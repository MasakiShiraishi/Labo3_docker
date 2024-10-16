package org.fungover.laboration2.service;

import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Warehouse {

    private List<Product> products;

    public Warehouse(List<Product> products) {
        this.products = List.copyOf(products); // Ensure immutability
    }

    // Method to add a new product
    public void addProduct(Product product) {
        if (product.name().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        // Use a mutable list to add the product
        List<Product> newProducts = new ArrayList<>(products);
        newProducts.add(product);

        products = List.copyOf(newProducts);
    }

    // Method to update an existing product
    public void updateProduct(Product updatedProduct) {
        products = new ArrayList<>(products); // mutable copy

        Optional<Product> existingProductOptional = products.stream()
                .filter(product -> product.id().equals(updatedProduct.id()))
                .findFirst();

        existingProductOptional.ifPresentOrElse(
                existingProduct -> {
                    // Remove the existing product and add the updated one
                    products.remove(existingProduct);
                    products.add(updatedProduct);
                },
                () -> {
                    throw new IllegalArgumentException("Product not found with id: " + updatedProduct.id());
                }
        );
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        return List.copyOf(products);
    }

    // Method to get a product by its ID
    public Optional<Product> getProductById(String id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findFirst();
    }

    // Method to get products belonging to a specific category sorted by name
    public List<Product> getProductsByCategorySortedByName(Category category) {
        return products.stream()
                .filter(product -> product.category() == category)
                .sorted((p1, p2) -> p1.name().compareTo(p2.name()))
                .toList();
    }

    // Method to get products created after a specified date
    public List<Product> getProductsCreatedAfter(LocalDate date) {
        return products.stream()
                .filter(product -> product.createdDate().isAfter(date))
                .toList();
    }

    // Method to get products that were modified after creation
    public List<Product> getProductsModifiedAfterCreated() {
        return products.stream()
                .filter(product -> product.getLastModifiedDate().isAfter(product.getCreatedDate()))
                .toList();
    }



}

