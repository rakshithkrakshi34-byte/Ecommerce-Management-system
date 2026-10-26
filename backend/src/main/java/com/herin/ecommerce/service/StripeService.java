package com.herin.ecommerce.service;

import com.herin.ecommerce.model.ProductEntity;
import com.herin.ecommerce.repository.ProductRepository;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StripeService {

    private final ProductRepository productRepository;

    @Value("${stripe.secret.key}")
    private String stripeApiKey;

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    public StripeService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    private boolean isAllowedFrontendUrl(
            java.net.URI candidate,
            java.net.URI allowedOrigin
    ) {
        return candidate.isAbsolute()
                && candidate.getHost() != null
                && candidate.getUserInfo() == null
                && candidate.getScheme().equalsIgnoreCase(allowedOrigin.getScheme())
                && candidate.getHost().equalsIgnoreCase(allowedOrigin.getHost())
                && effectivePort(candidate) == effectivePort(allowedOrigin);
    }

    private int effectivePort(java.net.URI uri) {
        if (uri.getPort() != -1) {
            return uri.getPort();
        }
        return "https".equalsIgnoreCase(uri.getScheme()) ? 443 : 80;
    }
    public String createCheckoutSession(
            List<Long> productIds,
            List<Long> quantities,
            String successUrl,
            String cancelUrl
    ) throws Exception {

        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("Your cart is empty.");
        }

        if (quantities == null || quantities.size() != productIds.size()) {
            throw new IllegalArgumentException(
                    "Product IDs and quantities must have the same number of items."
            );
        }

        if (successUrl == null || successUrl.isBlank()
                || cancelUrl == null || cancelUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "Success and cancel URLs are required."
            );
        }

        try {
            java.net.URI allowedOrigin = java.net.URI.create(frontendUrl);
            java.net.URI successUri = java.net.URI.create(successUrl);
            java.net.URI cancelUri = java.net.URI.create(cancelUrl);

            if (!isAllowedFrontendUrl(successUri, allowedOrigin)
                    || !isAllowedFrontendUrl(cancelUri, allowedOrigin)) {
                throw new IllegalArgumentException(
                        "Checkout redirect URLs must use the configured frontend origin."
                );
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid checkout redirect URL.");
        }

        SessionCreateParams.Builder builder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl);

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Long quantity = quantities.get(i);

            if (productId == null || productId <= 0) {
                throw new IllegalArgumentException("Invalid product ID.");
            }

            if (quantity == null || quantity <= 0) {
                throw new IllegalArgumentException(
                        "Product quantities must be greater than zero."
                );
            }

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found: " + productId
                    ));

            if (quantity > product.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            if (product.getName() == null || product.getName().isBlank()) {
                throw new IllegalArgumentException(
                        "Product name is missing for product ID " + productId
                );
            }

            BigDecimal price = product.getPrice();

            if (price == null || price.signum() <= 0) {
                throw new IllegalArgumentException(
                        "Product price is invalid for product ID " + productId
                );
            }

            long amountInPaise;
            try {
                amountInPaise = price
                        .movePointRight(2)
                        .longValueExact();
            } catch (ArithmeticException ex) {
                throw new IllegalArgumentException(
                        "Product price must have no more than two decimal places."
                );
            }

            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(product.getName())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("inr")
                            .setUnitAmount(amountInPaise)
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(priceData)
                            .setQuantity(quantity)
                            .build();

            builder.addLineItem(lineItem);
        }

        Session session = Session.create(builder.build());

        if (session.getUrl() == null || session.getUrl().isBlank()) {
            throw new IllegalStateException(
                    "Stripe did not return a checkout URL."
            );
        }

        return session.getUrl();
    }
}
