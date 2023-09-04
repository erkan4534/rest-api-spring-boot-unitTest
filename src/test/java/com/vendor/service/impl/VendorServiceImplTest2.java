package com.vendor.service.impl;

import com.vendor.exception.VendorNotFoundException;
import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(value = { MockitoExtension.class })
public class VendorServiceImplTest2 {


    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor("1","Amazon","USA","3232");
    }


    @Test
    void testDeleteVendor() {

        VendorRepository vendorRepository= mock(VendorRepository.class);
        VendorService vendorService = new VendorServiceImpl(vendorRepository);

        doNothing().when(vendorRepository).deleteById(any());
        assertThat(vendorService.deleteVendor("1")).isEqualTo("Success");
    }

    @Test
    void testGetVendor() {

        VendorRepository vendorRepository= mock(VendorRepository.class);
        VendorService vendorService = new VendorServiceImpl(vendorRepository);

        doReturn(Optional.ofNullable(vendor)).when(vendorRepository).findById("1");
        assertThat(vendorService.getVendor("1").getName()).isEqualTo(vendor.getName());
    }


    @Test
    void testGetVendorThrows() {

        VendorRepository vendorRepository= mock(VendorRepository.class);
        VendorService vendorService = new VendorServiceImpl(vendorRepository);

        doReturn(Optional.ofNullable(vendor)).when(vendorRepository).findById("1");
        assertThat(vendorService.getVendor("1").getName()).isEqualTo(vendor.getName());

        assertThrows(VendorNotFoundException.class,()->vendorService.getVendor("2"));

    }


    @Test
    void testGetVendorThrowsOther() {

        VendorRepository vendorRepository= mock(VendorRepository.class);
        VendorService vendorService = new VendorServiceImpl(vendorRepository);

        doThrow(new VendorNotFoundException("Requested Vendor does not exist"))
         .when(vendorRepository).findById("2");
        assertThrows(VendorNotFoundException.class,()->vendorService.getVendor("2"));
    }

}
