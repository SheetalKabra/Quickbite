package com.quickbite.backend.service;

import com.quickbite.backend.dto.ProductRequestDto;
import com.quickbite.backend.model.Category;
import com.quickbite.backend.model.Product;
import com.quickbite.backend.model.Vendor;
import com.quickbite.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final VendorService vendorService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, VendorService vendorService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.vendorService = vendorService;
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {
        return productRepository.save(convertDtoToProduct(productRequestDto));
    }

    @Override
    public List<Product> addBulkProduct(List<ProductRequestDto> productRequestDtoList) {
        List<Product> products = new ArrayList<>();
        for(ProductRequestDto productRequestDto : productRequestDtoList){
            products.add(convertDtoToProduct(productRequestDto));
        }
        return productRepository.saveAll(products);
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: "+id));
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    private Product convertDtoToProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        Category category = categoryService.getCategoryById(productRequestDto.getCategoryId());
        Vendor vendor = vendorService.getVendorById(productRequestDto.getVendorId());
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setIsAvailable(productRequestDto.getIsAvailable());
        product.setVegOrNonVeg(productRequestDto.getVegOrNonVeg());
        product.setCategory(category);
        product.setVendor(vendor);
        return product;
    }

}
