package org.fungover.laboration2.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    private final Validator validator;

    public ProductTest() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    @Test
    void shouldNotAllowEmptyName() {
        Product product = new Product(
                "3", "", Category.drink, 10,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(violation ->
                violation.getMessage().equals("Empty names not allowed"));
    }



    @Test
    void shouldNotAllowRatingLessThan1() {
        Product product = new Product(
                "3", "Milk", Category.drink, 0,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("Rating should not be less than 1");
    }

    @Test
    void shouldNotAllowRatingGreaterThan20() {
        Product product = new Product(
                "3", "Milk", Category.drink, 21,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Set<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(violations).isNotEmpty();
        assertThat(messages).contains("Rating should not be greater than 20");
    }

    @Test
    void shouldAllowValidPerson() {
        Product product = new Product(
                "3", "Milk", Category.drink, 10,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);


        assertThat(violations).isEmpty();
    }
}