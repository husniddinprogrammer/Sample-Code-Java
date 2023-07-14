package com.example.productmicroservice.integration;

import com.example.productmicroservice.controller.ProductController;
import com.example.productmicroservice.controller.utils.ControllerUtils;
import com.example.productmicroservice.dto.message.MessageDto;
import com.example.productmicroservice.dto.product.create.ProductCreateRequestDto;
import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.dto.product.update.ProductUpdateRequestDto;
import com.example.productmicroservice.service.ProductService;
import com.example.productmicroservice.utils.TestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ControllerUtils controllerUtils;
    @MockBean
    private ProductService productService;

    @Test
    public void testCreateProduct() throws Exception {
        ProductCreateRequestDto productRequestDto = new ProductCreateRequestDto();
        productRequestDto.setName(TestConstants.PRODUCT_NAME_1);
        productRequestDto.setCost(TestConstants.COST_1);
        productRequestDto.setCustomerId(TestConstants.CUSTOMER_ID_1);
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.PRODUCT_CREATED_MESSAGE)));
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.PRODUCT_CREATED_MESSAGE));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductUpdateRequestDto updatedProduct = new ProductUpdateRequestDto();
        updatedProduct.setName(TestConstants.PRODUCT_NAME_1);
        updatedProduct.setCost(TestConstants.COST_1);
        updatedProduct.setCustomerId(TestConstants.CUSTOMER_ID_1);
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.PRODUCT_UPDATED_MESSAGE)));
        mockMvc.perform(patch("/products/{id}", TestConstants.ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.PRODUCT_UPDATED_MESSAGE));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.PRODUCT_DELETED_MESSAGE)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", TestConstants.ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.PRODUCT_DELETED_MESSAGE));
    }

    @Test
    public void testSearchProductByName() throws Exception {
        List<ProductGetResponseDto> products = new ArrayList<>();
        products.add(new ProductGetResponseDto(TestConstants.ID, TestConstants.PRODUCT_NAME_1,TestConstants.COST_1,null,null,TestConstants.CUSTOMER_ID_1));
        Page<ProductGetResponseDto> productPage = new PageImpl<>(products);
        given(productService.searchProductsByName(TestConstants.PRODUCT_NAME_1, TestConstants.PAGE, TestConstants.SIZE, TestConstants.SORT_BY_ID)).willReturn(productPage);
        mockMvc.perform(get("/products/search")
                        .param("name", TestConstants.PRODUCT_NAME_1)
                        .param("page", String.valueOf(TestConstants.PAGE))
                        .param("size", String.valueOf(TestConstants.SIZE))
                        .param("sortBy", TestConstants.SORT_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(products.size())))
                .andExpect(jsonPath("$.content[0].name").value(products.get(0).getName()));
    }

    @Test
    public void testSearchProductByCost() throws Exception {
        List<ProductGetResponseDto> products = new ArrayList<>();
        products.add(new ProductGetResponseDto(TestConstants.ID, TestConstants.PRODUCT_NAME_1,TestConstants.COST_1,null,null,TestConstants.CUSTOMER_ID_1));
        Page<ProductGetResponseDto> productPage = new PageImpl<>(products);
        given(productService.searchProductsByCost(TestConstants.COST_1, TestConstants.PAGE, TestConstants.SIZE, TestConstants.SORT_BY_ID)).willReturn(productPage);
        mockMvc.perform(get("/products/search")
                        .param("cost", String.valueOf(TestConstants.COST_1))
                        .param("page", String.valueOf(TestConstants.PAGE))
                        .param("size", String.valueOf(TestConstants.SIZE))
                        .param("sortBy", TestConstants.SORT_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(products.size())))
                .andExpect(jsonPath("$.content[0].cost").value(products.get(0).getCost()));
    }

    @Test
    public void testSearchProductByCustomerId() throws Exception {
        List<ProductGetResponseDto> products = new ArrayList<>();
        products.add(new ProductGetResponseDto(TestConstants.ID, TestConstants.PRODUCT_NAME_1,TestConstants.COST_1,null,null,TestConstants.CUSTOMER_ID_1));
        Page<ProductGetResponseDto> productPage = new PageImpl<>(products);
        given(productService.searchProductsByCustomerId(TestConstants.CUSTOMER_ID_1, TestConstants.PAGE, TestConstants.SIZE, TestConstants.SORT_BY_ID)).willReturn(productPage);
        mockMvc.perform(get("/products/search")
                        .param("customerId", String.valueOf(TestConstants.CUSTOMER_ID_1))
                        .param("page", String.valueOf(TestConstants.PAGE))
                        .param("size", String.valueOf(TestConstants.SIZE))
                        .param("sortBy", TestConstants.SORT_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(products.size())))
                .andExpect(jsonPath("$.content[0].customerId").value(products.get(0).getCustomerId()));
    }
}
