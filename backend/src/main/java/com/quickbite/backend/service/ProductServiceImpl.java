package com.quickbite.backend.service;

import com.quickbite.backend.dto.CategoryResponseDto;
import com.quickbite.backend.dto.ProductRequestDto;
import com.quickbite.backend.dto.ProductResponseDto;
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
    public List<ProductResponseDto> getAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: productList){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setName(product.getName());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setCategory(new CategoryResponseDto(product.getCategory().getId(), product.getCategory().getName()));
            productResponseDto.setImage("http://localhost:8081/backend/uploads/images/products/"+product.getImageUrl());
            productResponseDto.setVegOrNonVeg(product.getVegOrNonVeg());
            productResponseDto.setPrice(product.getPrice());
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getProductByVendorId(Long vendorId) {
        List<Product> productList = productRepository.findByVendorId(vendorId);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: productList){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setName(product.getName());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setCategory(new CategoryResponseDto(product.getCategory().getId(), product.getCategory().getName()));
            productResponseDto.setImage(product.getImageUrl());
            productResponseDto.setVegOrNonVeg(product.getVegOrNonVeg());
            productResponseDto.setImage("http://localhost:8081/backend/uploads/images/products/"+product.getImageUrl());
            productResponseDto.setPrice(product.getPrice());
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getAllFeatuedProducts() {
        List<Product> productList = productRepository.findByIsFeaturedTrue();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: productList){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(product.getId());
            productResponseDto.setName(product.getName());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setCategory(new CategoryResponseDto(product.getCategory().getId(), product.getCategory().getName()));
            productResponseDto.setImage(product.getImageUrl());
            productResponseDto.setVegOrNonVeg(product.getVegOrNonVeg());
            productResponseDto.setImage("http://localhost:8081/backend/uploads/images/products/"+product.getImageUrl());
            productResponseDto.setPrice(product.getPrice());
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
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
