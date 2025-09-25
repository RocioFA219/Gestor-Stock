package com.example.product_microservice.product_microservice.service;

import com.example.product_microservice.product_microservice.dto.ProductRequest;
import com.example.product_microservice.product_microservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAllProduct();
    ProductResponse updateProduct(String id, ProductRequest request);
    void deleteById(String id);
}
