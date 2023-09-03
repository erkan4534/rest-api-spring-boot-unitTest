package com.vendor.service.impl;

import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(value = { MockitoExtension.class })
@DataJpaTest
public class VendorServiceImplTest2 {

    @Spy
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;

    Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor= new Vendor("1","Amazon","USA","1111");
        vendorRepository.save(vendor);
    }

    @AfterEach
    void tearDown() {
        //vendorRepository.deleteAll();
    }

    @Test
    void testCreateVendor() {
       //vendor = new Vendor("1","Amazon","USA","3232");
       // String result= vendorService.createVendor(vendor);
       // System.out.println(result);

        vendor = vendorService.getVendor("1");
        System.out.println(vendor);
    }

}
