package com.lididimi.restaurant.restaurant_categories.model.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "Category should be unique.";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}


