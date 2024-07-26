
package com.lididimi.restaurant.restaurant_categories.model.validation;


import com.lididimi.restaurant.restaurant_categories.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final CategoryRepository categoryRepository;

    public UniqueNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

        @Override
        public boolean isValid (String value, ConstraintValidatorContext constraintValidatorContext){
        return categoryRepository.findByName(value).isEmpty();
    }
}

