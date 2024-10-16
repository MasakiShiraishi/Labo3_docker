package org.fungover.laboration2.service;

import org.fungover.laboration2.entities.Category;
import org.fungover.laboration2.entities.Product;

import java.util.List;

public interface WarehouseService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategorySortedByName(Category category);

    void addProduct(Product product);

    int getCount();
}
