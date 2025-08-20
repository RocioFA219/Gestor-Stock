package com.example.product_microservice.product_microservice.service.Impl;

import com.example.product_microservice.product_microservice.client.InventoryClient;
import com.example.product_microservice.product_microservice.dto.InventoryRequest;
import com.example.product_microservice.product_microservice.dto.InventoryResponse;
import com.example.product_microservice.product_microservice.dto.ProductRequest;
import com.example.product_microservice.product_microservice.dto.ProductResponse;
import com.example.product_microservice.product_microservice.model.Product;
import com.example.product_microservice.product_microservice.repository.ProductRepository;
import com.example.product_microservice.product_microservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .supplier(productRequest.supplier())
                .build();
        productRepository.save(product);
        log.info("El producto se creo correctamente");
        InventoryRequest inventoryRequest = new InventoryRequest(product.getId(), 0);
        InventoryResponse inventoryResponse = inventoryClient.createInventory(inventoryRequest);
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSupplier(),
                inventoryResponse.stock()
        );
    }
    public ProductResponse getProductById(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado por ese id"));
       InventoryResponse inventoryResponse = inventoryClient.getInventoryById(product.getId());
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSupplier(),
                inventoryResponse.stock()
        );
    }
    public List<ProductResponse> getAllProduct(){
        return productRepository.findAll()
                .stream()
                .map(product ->
                        new ProductResponse(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getSupplier(),
                                0
                        ))
                .toList();
    }
    public ProductResponse updateProduct(String id, ProductRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no actualizado"));
        product.setPrice(request.price());
        product.setSupplier(request.supplier());
        productRepository.save(product);
                return new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getSupplier(),
                        0
                );
    }

    public void deleteById(String id){
        productRepository.deleteById(id);
        inventoryClient.deleteInventory(id);
    }
}
