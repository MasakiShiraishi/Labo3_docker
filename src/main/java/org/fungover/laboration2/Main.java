package org.fungover.laboration2;

import org.fungover.laboration2.entities.Product;
import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.service.Warehouse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        // Prepare list of products
        List<Product> initialProducts = List.of(
                new Product("1", "iPhone", Category.electronics,10,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 1)),
                new Product("2", "water", Category.drink, 15,
                        LocalDate.of(2024, 2, 1),
                        LocalDate.of(2024, 2, 1)),
                new Product("4", "picture book", Category.books, 17,
                        LocalDate.of(2024, 2, 16),
                        LocalDate.of(2024, 2, 16))
        );

        // Create a Warehouse instance
        Warehouse warehouse = new Warehouse(initialProducts);

        // Example of adding a product
        Product newProduct = new Product(
                "3", "milk", Category.drink, 8,
                LocalDate.now(), LocalDate.now());
        warehouse.addProduct(newProduct);

        // getting all products
        List<Product> allProducts = warehouse.getAllProducts();
        System.out.println("1. All products in the warehouse:");
        allProducts.forEach(System.out::println);

        // get a product by its ID
        Optional<Product> productById = warehouse.getProductById("2");
        productById.ifPresent(product -> System.out.println("\n2. Product found by ID(BEFORE updating):\n " + product));

        // updating a product
        Product updatedProduct = new Product("2", "water(updated)", Category.drink, 18,
                LocalDate.of(2024, 2, 1),
                LocalDate.now());
        warehouse.updateProduct(updatedProduct);

        Optional<Product> updatingProductById = warehouse.getProductById("2");
        updatingProductById.ifPresent(product -> System.out.println("\n3. Updated Product:\n " + product));


        // Example of getting a product by ID
        Optional<Product> productAById = warehouse.getProductById("2");
        productAById.ifPresent(product -> System.out.println("\n4. !!Product found by ID(AFTER updating):\n " + product));

        // Example of getting products belonging to a specific category sorted by name
        List<Product> productsByCategory = warehouse.getProductsByCategorySortedByName(Category.drink);
        System.out.println("\n5. Products in drink sorted by name:");
        productsByCategory.forEach(System.out::println);


        LocalDate specifiedDate = LocalDate.of(2024, 2, 15);
        List<Product> productsCreatedAfter = warehouse.getProductsCreatedAfter(specifiedDate);
        System.out.println("\n6. Products created after " + specifiedDate + ":");
        productsCreatedAfter.forEach(System.out::println);

        // Example of getting products modified after creation
        List<Product> modifiedProducts = warehouse.getProductsModifiedAfterCreated();
        System.out.println("\n7. Products modified after creation:");
        modifiedProducts.forEach(System.out::println);


    }
}

