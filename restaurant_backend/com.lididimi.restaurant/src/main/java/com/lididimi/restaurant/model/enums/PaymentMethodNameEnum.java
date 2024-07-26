package com.lididimi.restaurant.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethodNameEnum {
        DEBIT_CARD("Debit Card"),
        CREDIT_CARD("Credit Card"),
        CASH("Cash");

        private final String value;

        PaymentMethodNameEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static PaymentMethodNameEnum forValue(String value) {
            for (PaymentMethodNameEnum method : PaymentMethodNameEnum.values()) {
                if (method.getValue().equalsIgnoreCase(value)) {
                    return method;
                }
            }
            throw new IllegalArgumentException("Unknown enum type " + value);
        }
    }