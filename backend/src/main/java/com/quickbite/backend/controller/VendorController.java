package com.quickbite.backend.controller;

import com.quickbite.backend.dto.VendorByCategoryResponseDto;
import com.quickbite.backend.dto.VendorRequestDto;
import com.quickbite.backend.model.Vendor;
import com.quickbite.backend.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @PostMapping("/add")
    public ResponseEntity<Vendor> addVendor(@RequestBody VendorRequestDto vendorRequestDto){
        Vendor savedVendor = vendorService.addVendor(vendorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVendor);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Vendor>> addMultipleVendors(@RequestBody List<VendorRequestDto> vendorRequestDtoList){
        List<Vendor> savedVendorList = vendorService.saveAllVendors(vendorRequestDtoList);
        return ResponseEntity.ok(savedVendorList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Vendor>> getAllVendors(){
        List<Vendor> vendorList = vendorService.getAllVendors();
        return ResponseEntity.ok(vendorList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id){
        Vendor vendor = vendorService.getVendorById(id);
        return ResponseEntity.ok(vendor);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<VendorByCategoryResponseDto>> getVendorByCategory(@PathVariable Long categoryId){
        List<VendorByCategoryResponseDto> vendor = vendorService.getVendorByCategory(categoryId);
        return ResponseEntity.ok(vendor);
    }
}
