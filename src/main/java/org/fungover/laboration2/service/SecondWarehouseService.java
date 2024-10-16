package org.fungover.laboration2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;

import java.util.List;

@ApplicationScoped
@Named("Second")
public class SecondWarehouseService implements WarehouseService {

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategorySortedByName(Category category) {
        return List.of();
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}