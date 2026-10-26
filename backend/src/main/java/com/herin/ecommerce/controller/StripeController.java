package com.herin.ecommerce.controller;

import com.herin.ecommerce.dto.StripeRequestDTO;
import com.herin.ecommerce.service.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stripe")
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(
            @RequestBody StripeRequestDTO request
    ) {
        try {
            List<Long> productIds = request.getProductIds();
            List<Long> quantities = request.getQuantities();

            String checkoutUrl = stripeService.createCheckoutSession(
                    productIds,
                    quantities,
                    request.getSuccessUrl(),
                    request.getCancelUrl()
            );

            return ResponseEntity.ok(
                    Collections.singletonMap("url", checkoutUrl)
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(
                            "error",
                            "Unable to create the Stripe checkout session."
                    ));
        }
    }
}
