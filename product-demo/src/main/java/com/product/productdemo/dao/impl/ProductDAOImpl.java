package com.product.productdemo.dao.impl;

import com.product.productdemo.dao.ProductDAO;
import com.product.productdemo.model.Product;
import com.product.productdemo.utils.file.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private IFileReader fileReader;

    @Override
    public List<Product> getAllProducts() {
        return fileReader.readFile("products.json");
    }

    @Override
    public Product getProductById(int id) {
        return getAllProducts().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> getProductsByNamePrefix(String prefix) {
        return getAllProducts().stream()
                .filter(product -> product.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByPriceRange(int min, int max) {
        return getAllProducts().stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByRating(double rating) {
        return getAllProducts().stream()
                .filter(product -> product.getRating() == rating)
                .collect(Collectors.toList());
    }

}
