package com.quickbite.backend.service;

import com.quickbite.backend.dto.ProductRequestDto;
import com.quickbite.backend.dto.ProductResponseDto;
import com.quickbite.backend.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductRequestDto productRequestDto);
    List<Product> addBulkProduct(List<ProductRequestDto> productRequestDtoList);
    Product getProductById(Long id);
    List<ProductResponseDto> getAll();
    List<ProductResponseDto> getProductByVendorId(Long vendorId);
    List<ProductResponseDto> getAllFeatuedProducts();
}
