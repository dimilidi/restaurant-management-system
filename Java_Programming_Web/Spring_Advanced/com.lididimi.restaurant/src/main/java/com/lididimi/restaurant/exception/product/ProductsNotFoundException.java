package com.lididimi.restaurant.exception.product;

public class ProductsNotFoundException extends RuntimeException {
    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}