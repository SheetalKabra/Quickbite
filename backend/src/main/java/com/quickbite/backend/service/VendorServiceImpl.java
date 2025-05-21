package com.quickbite.backend.service;

import com.quickbite.backend.dto.VendorByCategoryResponseDto;
import com.quickbite.backend.dto.VendorRequestDto;
import com.quickbite.backend.model.Category;
import com.quickbite.backend.model.Vendor;
import com.quickbite.backend.repository.CategoryRepository;
import com.quickbite.backend.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{
    private final VendorRepository vendorRepository;
    private final CategoryService categoryService;

    public VendorServiceImpl(VendorRepository vendorRepository, CategoryService categoryService) {
        this.vendorRepository = vendorRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Vendor addVendor(VendorRequestDto vendorRequestDto) {
        Category category = categoryService.getCategoryById(vendorRequestDto.getCategoryId());
        //save to vendor table
        Vendor vendor = new Vendor();
        vendor.setCategory(category);
        vendor.setName(vendorRequestDto.getName());
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> saveAllVendors(List<VendorRequestDto> vendorRequestDtoList) {
        List<Vendor> vendors = new ArrayList<>();
        for(VendorRequestDto vendorRequestDto: vendorRequestDtoList){
            Category category = categoryService.getCategoryById(vendorRequestDto.getCategoryId());
            Vendor vendor = new Vendor();
            vendor.setCategory(category);
            vendor.setName(vendorRequestDto.getName());
            vendors.add(vendor);
        }
        System.out.println("vendors: "+vendors);
        return vendorRepository.saveAll(vendors);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: "+ vendorId));
        return vendor;
    }

    @Override
    public List<VendorByCategoryResponseDto> getVendorByCategory(Long categoryId) {
        List<Vendor> vendorList = vendorRepository.findVendorByCategoryId(categoryId);
        List<VendorByCategoryResponseDto> vendorByCategoryResponseDtoList = new ArrayList<>();
        for(Vendor vendor: vendorList){
            VendorByCategoryResponseDto vendorByCategoryResponseDto = new VendorByCategoryResponseDto();
            vendorByCategoryResponseDto.setId(vendor.getId());
            vendorByCategoryResponseDto.setName(vendor.getName());
            vendorByCategoryResponseDto.setImageUrl("http://localhost:8081/backend/uploads/images/vendors/"+vendor.getImageUrl());
            vendorByCategoryResponseDto.setRating(vendor.getRating());
            vendorByCategoryResponseDto.setDiscountText(vendor.getDiscountText());
            vendorByCategoryResponseDto.setDeliveryTimeInMinutes(vendor.getDeliveryTimeInMinutes());
            vendorByCategoryResponseDto.setPriceForOne(vendor.getPriceForOne());
            vendorByCategoryResponseDtoList.add(vendorByCategoryResponseDto);
            vendorByCategoryResponseDto.setCuisines(vendor.getCuisines());
        }
        return vendorByCategoryResponseDtoList;
    }


}
