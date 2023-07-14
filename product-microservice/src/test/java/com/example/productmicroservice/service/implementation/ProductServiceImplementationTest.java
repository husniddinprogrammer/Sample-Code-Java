package com.example.productmicroservice.service.implementation;

import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.dto.product.update.ProductUpdateRequestDto;
import com.example.productmicroservice.entity.Product;
import com.example.productmicroservice.exceptions.product.ProductAlreadyExistException;
import com.example.productmicroservice.kafka.Producer;
import com.example.productmicroservice.repository.ProductRepository;
import com.example.productmicroservice.service.ProductService;
import com.example.productmicroservice.utils.TestConstants;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplementationTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductService productService;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Producer producer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImplementation(productRepository, entityManager, producer);
    }

    @Test
    void createProduct_CreatesProduct_WhenProductDoesNotExist() {
        Product product = createProduct(TestConstants.PRODUCT_NAME_1, TestConstants.COST_1, TestConstants.CUSTOMER_ID_1);
        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        productService.createProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void createProduct_ThrowsProductAlreadyExistException_WhenProductExists() {
        Product product = createProduct(TestConstants.PRODUCT_NAME_1, TestConstants.COST_1, TestConstants.CUSTOMER_ID_1);
        when(productRepository.existsByName(product.getName())).thenReturn(true);
        assertThrows(ProductAlreadyExistException.class, () -> productService.createProduct(product));
    }

    @Test
    void getAllProducts_ReturnsAllProducts() {
        List<Product> productList = createProductList();
        when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));
        Page<ProductGetResponseDto> result = productService.getAllProducts(TestConstants.PAGE, TestConstants.SIZE, TestConstants.SORT_BY_ID);
        assertEquals(productList.size(), result.getContent().size());
    }

    @Test
    void searchProductsByName_ReturnsMatchingProducts() {
        List<Product> productList = createProductList();
        Pageable pageable = PageRequest.of(TestConstants.PAGE, TestConstants.SIZE, Sort.by(TestConstants.SORT_BY_ID));
        Page<Product> expectedPage = new PageImpl<>(productList, pageable, productList.size());
        when(productRepository.findByName(eq(TestConstants.PRODUCT_NAME_1), any(Pageable.class)))
                .thenReturn(expectedPage);
        Page<ProductGetResponseDto> result = productService.searchProductsByName(TestConstants.PRODUCT_NAME_1, 0, 10, "id");
        assertEquals(expectedPage.getTotalElements(), result.getTotalElements());
        assertEquals(expectedPage.getNumber(), result.getNumber());
        assertEquals(productList.size(), result.getContent().size());
    }



    private Product createProduct(String name, BigDecimal cost, Long customerId) {
        Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        product.setCustomerId(customerId);
        return product;
    }

    private List<Product> createProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct(TestConstants.PRODUCT_NAME_1, TestConstants.COST_1, TestConstants.CUSTOMER_ID_1));
        productList.add(createProduct(TestConstants.PRODUCT_NAME_2, TestConstants.COST_2, TestConstants.CUSTOMER_ID_2));
        return productList;
    }

    private ProductUpdateRequestDto createUpdatedProduct(String name, BigDecimal cost, Long customerId) {
        ProductUpdateRequestDto productDto = new ProductUpdateRequestDto();
        productDto.setName(name);
        productDto.setCost(cost);
        productDto.setCustomerId(customerId);
        return productDto;
    }

}
