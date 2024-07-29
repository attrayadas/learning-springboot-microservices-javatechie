package com.attraya;

import com.attraya.controller.ProductController;
import com.attraya.entity.Product;
import com.attraya.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Sd10SpringdataJpaApplication.class)
@AutoConfigureMockMvc // it will load web layer for us
@Slf4j
class Sd10SpringdataJpaApplicationTests {

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    public void addProductTest() throws Exception {
        log.info("Sd10SpringdataJpaApplicationTests.addProductTest :: started");
        Product demoProduct = new Product(1000, "sample product description", "sample product type", "sample product name");
        when(productRepository.save(any())).thenReturn(demoProduct);
        // URL: /products
        // HTTP METHOD: POST
        // Request & Response: Product (JSON String)

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/products")
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andDo(print()) // it will print details in the console
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()); // for jsonpath: https://jsonpath.com/

    }

    @Test
    public void getProductsShouldReturnAllProducts() throws Exception {
        log.info("Sd10SpringdataJpaApplicationTests.getProductsShouldReturnAllProducts :: started");
        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(1, "sample product type 1", 1000, "sample product description 1", "sample product name 1"),
                new Product(2, "sample product type 2", 2000, "sample product description 2", "sample product name 2")));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/products")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        log.info("Sd10SpringdataJpaApplicationTests.getProductByIdTest :: started");
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product(108, "sample product type 1", 1000, "sample product description 1", "sample product name 1")));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/products/" + 108)
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(108));
    }

    @Test
    public void updateProductTest() throws Exception {
        log.info("Sd10SpringdataJpaApplicationTests.updateProductTest :: started");

        Product demoProduct = new Product(1, "sample product type 1", 1000, "sample product description 1", "sample product name 1");
        when(productRepository.findById(1)).thenReturn(Optional.of(demoProduct));
        when(productRepository.save(any())).thenReturn(new Product(1, "sample product type 1 updated", 10020, "sample product description 1 updated", "sample product name 1 updated"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/products/{id}", 1)
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("sample product type 1 updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("sample product description 1 updated"));
    }

    @Test
    public void deleteProductByIdTest() throws Exception {
        log.info("Sd10SpringdataJpaApplicationTests.deleteProductTest :: started");

        doNothing().when(productRepository).deleteById(anyInt());
        when(productRepository.count()).thenReturn(100L);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/products/{id}", 1)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private String convertObjectAsString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
