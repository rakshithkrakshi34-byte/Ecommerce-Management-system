package com.herin.ecommerce.config;

import com.herin.ecommerce.model.ProductEntity;
import com.herin.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductDataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        if (productRepository.count() > 0) {
            System.out.println("Product seed skipped: products already exist.");
            return;
        }

        List<ProductEntity> products = List.of(

                new ProductEntity(
                        "Wireless Bluetooth Headphones",
                        "High-quality wireless Bluetooth headphones with clear sound and comfortable ear cushions.",
                        new BigDecimal("2499.00"),
                        "https://via.placeholder.com/300?text=Bluetooth+Headphones",
                        "Electronics",
                        25
                ),

                new ProductEntity(
                        "Smartphone",
                        "Modern smartphone with a high-resolution display, powerful processor and long-lasting battery.",
                        new BigDecimal("15999.00"),
                        "https://via.placeholder.com/300?text=Smartphone",
                        "Electronics",
                        20
                ),

                new ProductEntity(
                        "Laptop",
                        "High-performance laptop suitable for work, development, entertainment and everyday use.",
                        new BigDecimal("54999.00"),
                        "https://via.placeholder.com/300?text=Laptop",
                        "Electronics",
                        15
                ),

                new ProductEntity(
                        "Smart Watch Series 5",
                        "Smart watch with fitness tracking, notifications, health monitoring and multiple sports modes.",
                        new BigDecimal("4999.00"),
                        "https://via.placeholder.com/300?text=Smart+Watch",
                        "Electronics",
                        30
                ),

                new ProductEntity(
                        "Wireless Mechanical Keyboard",
                        "Wireless mechanical keyboard with responsive keys and a comfortable typing experience.",
                        new BigDecimal("3499.00"),
                        "https://via.placeholder.com/300?text=Mechanical+Keyboard",
                        "Electronics",
                        20
                ),

                new ProductEntity(
                        "Gaming Mouse",
                        "Precision gaming mouse with responsive controls and ergonomic design.",
                        new BigDecimal("1599.00"),
                        "https://via.placeholder.com/300?text=Gaming+Mouse",
                        "Electronics",
                        35
                ),

                new ProductEntity(
                        "USB-C Fast Charger",
                        "Compact USB-C fast charger designed for compatible smartphones, tablets and accessories.",
                        new BigDecimal("1299.00"),
                        "https://via.placeholder.com/300?text=USB-C+Charger",
                        "Electronics",
                        40
                ),

                new ProductEntity(
                        "Portable Bluetooth Speaker",
                        "Portable Bluetooth speaker delivering clear audio with a compact travel-friendly design.",
                        new BigDecimal("2199.00"),
                        "https://via.placeholder.com/300?text=Bluetooth+Speaker",
                        "Electronics",
                        30
                ),

                new ProductEntity(
                        "Men's Casual Jacket",
                        "Comfortable casual jacket suitable for everyday wear and outdoor activities.",
                        new BigDecimal("2999.00"),
                        "https://via.placeholder.com/300?text=Casual+Jacket",
                        "Fashion",
                        20
                ),

                new ProductEntity(
                        "Men's Running Shoes",
                        "Lightweight running shoes designed for comfort, support and everyday exercise.",
                        new BigDecimal("3499.00"),
                        "https://via.placeholder.com/300?text=Running+Shoes",
                        "Fashion",
                        25
                ),

                new ProductEntity(
                        "Women's Handbag",
                        "Stylish everyday handbag with practical storage compartments and durable construction.",
                        new BigDecimal("2499.00"),
                        "https://via.placeholder.com/300?text=Handbag",
                        "Fashion",
                        18
                ),

                new ProductEntity(
                        "Classic Sunglasses",
                        "Classic sunglasses with a stylish frame suitable for everyday outdoor use.",
                        new BigDecimal("1499.00"),
                        "https://via.placeholder.com/300?text=Sunglasses",
                        "Fashion",
                        30
                ),

                new ProductEntity(
                        "Cotton Casual T-Shirt",
                        "Comfortable cotton casual T-shirt suitable for everyday wear.",
                        new BigDecimal("799.00"),
                        "https://via.placeholder.com/300?text=T-Shirt",
                        "Fashion",
                        50
                ),

                new ProductEntity(
                        "Denim Jeans",
                        "Durable denim jeans with a comfortable fit for everyday use.",
                        new BigDecimal("1999.00"),
                        "https://via.placeholder.com/300?text=Denim+Jeans",
                        "Fashion",
                        35
                ),

                new ProductEntity(
                        "Coffee Maker",
                        "Convenient coffee maker designed for preparing fresh coffee at home.",
                        new BigDecimal("4499.00"),
                        "https://via.placeholder.com/300?text=Coffee+Maker",
                        "Home & Kitchen",
                        15
                ),

                new ProductEntity(
                        "Non-Stick Cookware Set",
                        "Durable non-stick cookware set for convenient everyday cooking.",
                        new BigDecimal("3999.00"),
                        "https://via.placeholder.com/300?text=Cookware+Set",
                        "Home & Kitchen",
                        20
                ),

                new ProductEntity(
                        "Stainless Steel Water Bottle",
                        "Reusable stainless steel water bottle designed to keep beverages fresh.",
                        new BigDecimal("899.00"),
                        "https://via.placeholder.com/300?text=Water+Bottle",
                        "Home & Kitchen",
                        45
                ),

                new ProductEntity(
                        "Modern Table Lamp",
                        "Modern table lamp providing practical lighting for desks, bedrooms and living spaces.",
                        new BigDecimal("1299.00"),
                        "https://via.placeholder.com/300?text=Table+Lamp",
                        "Home & Kitchen",
                        25
                ),

                new ProductEntity(
                        "Yoga Mat",
                        "Comfortable non-slip yoga mat suitable for yoga, stretching and home workouts.",
                        new BigDecimal("999.00"),
                        "https://via.placeholder.com/300?text=Yoga+Mat",
                        "Sports",
                        40
                ),

                new ProductEntity(
                        "Adjustable Dumbbell Set",
                        "Adjustable dumbbell set suitable for strength training and home workouts.",
                        new BigDecimal("4999.00"),
                        "https://via.placeholder.com/300?text=Dumbbell+Set",
                        "Sports",
                        12
                ),

                new ProductEntity(
                        "Football",
                        "Durable football suitable for training, recreation and competitive play.",
                        new BigDecimal("899.00"),
                        "https://via.placeholder.com/300?text=Football",
                        "Sports",
                        30
                ),

                new ProductEntity(
                        "Cricket Bat",
                        "Quality cricket bat suitable for recreational and competitive cricket.",
                        new BigDecimal("2499.00"),
                        "https://via.placeholder.com/300?text=Cricket+Bat",
                        "Sports",
                        18
                ),

                new ProductEntity(
                        "Programming Fundamentals Book",
                        "Beginner-friendly programming book covering fundamental programming concepts.",
                        new BigDecimal("699.00"),
                        "https://via.placeholder.com/300?text=Programming+Book",
                        "Books",
                        30
                ),

                new ProductEntity(
                        "DevOps Engineering Handbook",
                        "Practical handbook covering DevOps engineering principles, automation and delivery practices.",
                        new BigDecimal("1199.00"),
                        "https://via.placeholder.com/300?text=DevOps+Book",
                        "Books",
                        25
                ),

                new ProductEntity(
                        "Laptop Backpack",
                        "Durable laptop backpack with compartments for computers, accessories and everyday items.",
                        new BigDecimal("1799.00"),
                        "https://via.placeholder.com/300?text=Laptop+Backpack",
                        "Accessories",
                        30
                ),

                new ProductEntity(
                        "Tablet 10 Inch",
                        "10-inch tablet suitable for entertainment, browsing, reading and everyday productivity.",
                        new BigDecimal("18999.00"),
                        "https://via.placeholder.com/300?text=Tablet",
                        "Electronics",
                        20
                ),

                new ProductEntity(
                        "Travel Backpack",
                        "Spacious travel backpack designed for trips, commuting and everyday carrying.",
                        new BigDecimal("2299.00"),
                        "https://via.placeholder.com/300?text=Travel+Backpack",
                        "Accessories",
                        25
                )
        );

        productRepository.saveAll(products);

        System.out.println("Product seed completed: " + products.size() + " products inserted.");
    }
}
