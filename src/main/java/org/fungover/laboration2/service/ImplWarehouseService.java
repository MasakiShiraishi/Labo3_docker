package org.fungover.laboration2.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.NotFoundException;
import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequestScoped
@Named("Impl")
public class ImplWarehouseService implements WarehouseService {

    private List<Product> products = Collections.synchronizedList(new ArrayList<Product>());
    private final Lock lock = new ReentrantLock();
//    public ImplWarehouseService(){
//        System.out.println("PersonService object created");
//    }
public ImplWarehouseService() {
    System.out.println("products created");

    products.addAll(List.of(
            new Product("1", "iPhone", Category.electronics, 10,
                    LocalDate.of(2024, 1, 1),
                    LocalDate.of(2024, 1, 1)),
            new Product("2", "water", Category.drink, 15,
                    LocalDate.of(2024, 2, 1),
                    LocalDate.of(2024, 2, 1)),
            new Product("4", "picture book", Category.books, 17,
                    LocalDate.of(2024, 2, 16),
                    LocalDate.of(2024, 2, 16))
    ));
}
    @Override
    public List<Product> getAllProducts() {
        lock.lock();
        try {
            System.out.println("Getting all products...");
            System.out.println("Products retrieved: " + products.size());
            return products;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Product> getProductsByCategorySortedByName(Category category) {
        System.out.println("Fetching products for category: " + category);
        List<Product> filteredProducts = products.stream()
                .filter(product -> product.category().equals(category))
                .sorted(Comparator.comparing(Product::name))
                .toList();
        System.out.println("Products retrieved: " + filteredProducts.size());
        return filteredProducts;
    }

    @Override
    public void addProduct(Product product) {
        lock.lock();
        try {
            products.add(product);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getCount() {
        return products.size();
    }
}
