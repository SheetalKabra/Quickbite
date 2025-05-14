package com.quickbite.backend.service;

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


}
