package com.vendor.controller;

import com.vendor.response.ResponseHandler;
import com.vendor.service.VendorService;
import com.vendor.model.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vendor")
@AllArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVendorDetail(@PathVariable("id") String id) {
       return ResponseHandler.responseBuilder("Requested Vendor Detail is given here",
                HttpStatus.OK, vendorService.getVendor(id));
    }

    @GetMapping("/")
    public List<Vendor> getAllVendorsDetails() {
        return vendorService.getAllVendors();
    }

    @PostMapping("/")
    public String createVendorDetail(@RequestBody Vendor vendor) {
        vendorService.createVendor(vendor);
        return "Vendor Created Successfully";
    }

    @PutMapping("/")
    public String updateVendorDetail(@RequestBody Vendor vendor) {
        vendorService.updateVendor(vendor);
        return "Vendor Updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteVendorDetail(@PathVariable("id") String id) {
        vendorService.deleteVendor(id);
        return "Vendor Deleted Successfully";
    }
}
