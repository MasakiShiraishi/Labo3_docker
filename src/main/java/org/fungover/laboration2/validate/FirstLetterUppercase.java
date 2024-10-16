package org.fungover.laboration2.validate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = {ProductValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstLetterUppercase {
    String message() default "Invalid first letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
