/*
package com.lididimi.restaurant.model.validation;

import com.lididimi.restaurant.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String>{

    private final CategoryRepository categoryRepository;

    public UniqueCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

        @Override
        public boolean isValid (String value, ConstraintValidatorContext constraintValidatorContext){
        return categoryRepository.existsByName(value);
    }
}
*/
