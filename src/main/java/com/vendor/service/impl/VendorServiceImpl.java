package com.vendor.service.impl;

import com.vendor.exception.VendorNotFoundException;
import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public String createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return "Success";
    }

    @Override
    public String updateVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return "Success";
    }

    @Override
    public String deleteVendor(String id) {
        vendorRepository.deleteById(id);
        return "Success";
    }

    @Override
    public Vendor getVendor(String id) {
       Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        if(vendorOptional.isEmpty())
            throw new VendorNotFoundException("Requested Vendor does not exist");
        return vendorOptional.get();
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public List<Vendor> getByName(String name) {
        return vendorRepository.findByName(name);
    }
}
