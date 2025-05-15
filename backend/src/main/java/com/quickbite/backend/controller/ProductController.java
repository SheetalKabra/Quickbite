package com.quickbite.backend.controller;

import com.quickbite.backend.dto.ProductRequestDto;
import com.quickbite.backend.model.Product;
import com.quickbite.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product savedProduct = productService.addProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @PostMapping("/addBulk")
    public ResponseEntity<List<Product>> addBulkProduct(@RequestBody List<ProductRequestDto> productRequestDtoList){
        List<Product> savedProductList = productService.addBulkProduct(productRequestDtoList);
        return ResponseEntity.ok(savedProductList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProduct();
        return ResponseEntity.ok(productList);
    }


}
