package com.vendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vendor.model.Vendor;
import com.vendor.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;
    Vendor vendorOne;
    Vendor vendorTwo;
    List<Vendor> vendorList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        vendorOne = new Vendor("1","Amazon","USA","12345");
        vendorTwo = new Vendor("2","GCP","UK","12345");
        vendorList.add(vendorOne);
        vendorList.add(vendorTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetVendorDetail() throws Exception {
        when(vendorService.getVendor("1")).thenReturn(vendorOne);
        this.mockMvc.perform(get("/vendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllVendorsDetails() throws Exception{
        when(vendorService.getAllVendors()).thenReturn(vendorList);
        this.mockMvc.perform(get("/vendor/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateVendorDetail() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(vendorOne);

        when(vendorService.createVendor(vendorOne)).thenReturn("Success");
        this.mockMvc.perform(post("/vendor/")
                .contentType(MediaType.APPLICATION_GRAPHQL_VALUE)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateVendorDetail() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(vendorOne);

        when(vendorService.updateVendor(vendorOne)).thenReturn("Success");
        this.mockMvc.perform(put("/vendor/")
                        .contentType(MediaType.APPLICATION_GRAPHQL_VALUE)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteVendorDetail() throws Exception {
        when(vendorService.deleteVendor("1")).thenReturn("Success");
        this.mockMvc.perform(delete("/vendor/1")).andDo(print()).andExpect(status().isOk());
    }
}
