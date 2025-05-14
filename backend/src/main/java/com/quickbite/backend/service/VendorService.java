package com.quickbite.backend.service;

import com.quickbite.backend.dto.VendorRequestDto;
import com.quickbite.backend.model.Vendor;

import java.util.List;

public interface VendorService {
    Vendor addVendor(VendorRequestDto vendorRequestDto);
    List<Vendor> saveAllVendors(List<VendorRequestDto> vendorRequestDtoList);
    Vendor getVendorById(Long vendorId);
    List<Vendor> getAllVendors();
}
