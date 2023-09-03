package com.vendor.repository;

import com.vendor.model.Vendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class VendorRepositoryTest {

    //jpaRepository içersinde glen findById gibi gelen metotlar test edildiğin için test yazmaya gerek yok
//yeni yazdığımız metotları test etmemiz gerekir.

    //given - when -then

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
       vendor= null;
        vendorRepository.deleteAll();
    }

    //Test case Success

    @Test
    void testFindByName_Found(){
        List<Vendor> vendorList = vendorRepository.findByName("Amazon");
        assertThat(vendorList.get(0).getId()).isEqualTo(vendor.getId());
        assertThat(vendorList.get(0).getAddress()).isEqualTo(vendor.getAddress());
    }

    //Test case Failure

    @Test
    void testFindByName_NotFound(){
        List<Vendor> vendorList = vendorRepository.findByName("GCP");
        assertThat(vendorList.isEmpty()).isTrue();
    }


}
