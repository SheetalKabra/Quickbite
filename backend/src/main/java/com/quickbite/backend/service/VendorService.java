package com.quickbite.backend.service;

import com.quickbite.backend.model.Vendor;

public interface VendorService {
    Vendor addVendor(Vendor vendor);
    Vendor getVendorById(Long vendorId);
}
