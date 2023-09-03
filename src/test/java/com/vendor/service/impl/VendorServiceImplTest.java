package com.vendor.service.impl;

import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;
    private VendorService vendorService;
    Vendor vendor;


    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository);
        vendor = new Vendor("1","Amazon","USA","3232");
    }

    @Test
    void when_testCreateVendor_success() {
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.createVendor(vendor)).isEqualTo("Success");
    }

    @Test
    void when_testCreateVendor_fail() {
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.createVendor(vendor)).isEqualTo("fail");
    }

    @Test
    void testUpdateVendor() {
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.updateVendor(vendor)).isEqualTo("Success");
    }

    @Test
    void testGetVendor() {
        when(vendorRepository.findById("1")).thenReturn(Optional.ofNullable(vendor));
        assertThat(vendorService.getVendor("1").getName()).isEqualTo(vendor.getName());
    }

    @Test
    void testGetByName() {
        when(vendorRepository.findByName("Amazon")).thenReturn(new ArrayList<>(Collections.singleton(vendor)));
        assertThat(vendorService.getByName("Amazon").get(0).getName()).isEqualTo(vendor.getName());
    }

    @Test
    void testGetAllVendors() {
        when(vendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(vendor)));
        assertThat(vendorService.getAllVendors().get(0).getPhoneNumber()).isEqualTo(vendor.getPhoneNumber());
    }

    @Test
    void testDeleteVendor() {
       doAnswer(Answers.CALLS_REAL_METHODS).when(vendorRepository).deleteById(any());
       assertThat(vendorService.deleteVendor("1")).isEqualTo("Success");
    }

    @Test
    void testCreateVendorForArgument() {
        //given

        //when
        vendorService.createVendor(vendor);
        //then
        ArgumentCaptor<Vendor> vendorArgumentCaptor = ArgumentCaptor.forClass(Vendor.class);
        verify(vendorRepository).save(vendorArgumentCaptor.capture());
        Vendor capturedVendor= vendorArgumentCaptor.getValue();
        assertThat(capturedVendor).isEqualTo(vendor);

    }


}
