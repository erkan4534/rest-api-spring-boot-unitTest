package com.vendor.repository;

import com.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, String> {
    List<Vendor> findByName(String name);
}
