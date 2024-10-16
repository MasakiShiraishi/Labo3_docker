package org.fungover.laboration2.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;

public class ProductValidator implements ConstraintValidator<FirstLetterUppercase, String>{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty())
            return false;
        return Character.isUpperCase(s.toCharArray()[0]);
    }
}
