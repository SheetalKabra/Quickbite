package com.quickbite.backend.repository;

import com.quickbite.backend.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findVendorByCategoryId(Long categoryId);
}
