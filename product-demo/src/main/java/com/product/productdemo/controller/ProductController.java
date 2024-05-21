package com.product.productdemo.controller;

import com.product.productdemo.PageResponseImpl;
import com.product.productdemo.model.PageResponse;
import com.product.productdemo.model.Product;
import com.product.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getAllProducts(Model model,
                                 @RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "9") int pageSize) {
        PageResponse<Product> pageData = new PageResponseImpl<>(productService.getAllProducts(), page, pageSize);
        model.addAttribute("pageData", pageData);
        return "product-list";
    }

    @GetMapping("/products/{id}")
    public String getProductById(Model model, @PathVariable int id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/products/name-starts/{prefix}")
    public String getProductsByNamePrefix(Model model, @PathVariable String prefix) {
        List<Product> products = productService.getProductsByNamePrefix(prefix);
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/products/price/{min}/{max}")
    public String getProductsByPriceRange(Model model, @PathVariable int min, @PathVariable int max) {
        List<Product> products = productService.getProductsByPriceRange(min, max);
        model.addAttribute("products", products);
        return "product-price-range";
    }

    @GetMapping("/products/rating/{rating}")
    public String getProductsByRating(Model model, @PathVariable double rating) {
        List<Product> products = productService.getProductsByRating(rating);
        model.addAttribute("products", products);
        return "product-rating";
    }

}
