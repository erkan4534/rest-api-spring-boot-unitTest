package com.vendor.service.impl;

import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTestToSpy {
    //spy her zaman gerçek nesneyi çağırır ama isterseniz farklı davranış verebilirsiniz.
    //Hem gerçek nesneyi  hem de mock nesnesini çağırabiliyorsunuz yani  istediğiniz gibi
    //davranış verebiliyorsunuz.

    @Mock
    private VendorRepository vendorRepository;


    @Test
    void testUpdateVendor() {

        Vendor firstVendor = new Vendor("2","Google","USA","1234");
        Vendor secondVendor = new Vendor("1","Amazon","USA","3232");

        VendorService vendorService = spy(new VendorServiceImpl(vendorRepository));
       // assertThat(vendorService.updateVendor(firstVendor)).isEqualTo("Success");
        vendorService.updateVendor(firstVendor);

        doReturn("Success").when(vendorService).updateVendor(secondVendor);
        vendorService.updateVendor(secondVendor);
    }

    @Test
    void testUpdateVendorSecondCondition() {


        Vendor firstVendor = new Vendor("2","Google","USA","1234");
        Vendor secondVendor = new Vendor("1","Amazon","USA","3232");
        Vendor thirdVendor = new Vendor("3","Amazon","USA","3232");

        VendorService vendorService = spy(new VendorServiceImpl(vendorRepository));

        doReturn("Success").when(vendorService).updateVendor(firstVendor);
        vendorService.updateVendor(firstVendor);

        doReturn("Success").when(vendorService).updateVendor(secondVendor);
        vendorService.updateVendor(secondVendor);

        doCallRealMethod().when(vendorService).updateVendor(thirdVendor);
        vendorService.updateVendor(thirdVendor);
    }

}
