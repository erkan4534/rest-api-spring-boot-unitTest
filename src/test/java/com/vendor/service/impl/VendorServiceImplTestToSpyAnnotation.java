package com.vendor.service.impl;

import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTestToSpyAnnotation {

    @Spy
    private VendorService vendorService = new VendorServiceImpl(mock(VendorRepository.class));

    @Test
    void testUpdateVendorSecondCondition() {

        Vendor firstVendor = new Vendor("2","Google","USA","1234");
        Vendor secondVendor = new Vendor("1","Amazon","USA","3232");
        Vendor thirdVendor = new Vendor("3","Amazon","USA","3232");

        doReturn("Success").when(vendorService).updateVendor(firstVendor);
        vendorService.updateVendor(firstVendor);

        doReturn("Success").when(vendorService).updateVendor(secondVendor);
        vendorService.updateVendor(secondVendor);

        doCallRealMethod().when(vendorService).updateVendor(thirdVendor);
        vendorService.updateVendor(thirdVendor);
    }
}
