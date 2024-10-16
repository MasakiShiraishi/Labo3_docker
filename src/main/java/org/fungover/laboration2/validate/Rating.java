package org.fungover.laboration2.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Min(value = 1, message = "Rating should not be less than 1")
@Max(value = 20, message = "Rating should not be greater than 20")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rating {
    String message() default "Rating should be between 1 and 20";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
