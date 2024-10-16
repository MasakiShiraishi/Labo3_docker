package org.fungover.laboration2.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.fungover.laboration2.validate.FirstLetterUppercase;

import java.time.LocalDate;

public record Product(
        String id,
        @NotEmpty(message = "Empty names not allowed")
        @FirstLetterUppercase
        String name,
        Category category,
        @Min(value = 1, message = "Rating should not be less than 1")
        @Max(value = 20, message = "Rating should not be greater than 20")
        int rating,
        LocalDate createdDate,
        LocalDate lastModifiedDate
){
//    public Product {
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Product name cannot be null or empty");
//        }
//    }
    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Product update(String name, int rating) {
        return new Product(this.id, name, this.category, rating, this.createdDate, LocalDate.now());
    }


    public LocalDate getCreatedDate() {
        return createdDate;
    }
}

