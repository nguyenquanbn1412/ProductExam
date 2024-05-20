package com.product.productdemo.dao;

import com.product.productdemo.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> getProductsByNamePrefix(String prefix);
    List<Product> getProductsByPriceRange(int min, int max);
    List<Product> getProductsByRating(double rating);

}
