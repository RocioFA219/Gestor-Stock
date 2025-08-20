package com.example.product_microservice.product_microservice.controller;
import com.example.product_microservice.product_microservice.dto.ProductRequest;
import com.example.product_microservice.product_microservice.dto.ProductResponse;
import com.example.product_microservice.product_microservice.service.Impl.ProductServiceImpl;
import com.example.product_microservice.product_microservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable String id,@RequestBody ProductRequest request){
        return productService.updateProduct(id,request);
    }
    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable String id){
        productService.deleteById(id);
    }
    }




