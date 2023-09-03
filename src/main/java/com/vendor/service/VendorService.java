package com.vendor.service;

import com.vendor.model.Vendor;
import java.util.List;

public interface VendorService {
     String createVendor(Vendor vendor);
     String updateVendor(Vendor vendor);
     String deleteVendor(String id);
     Vendor getVendor(String id);
     List<Vendor> getAllVendors();
     List<Vendor> getByName(String name);
}
