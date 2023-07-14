package com.example.productmicroservice.service;

import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.dto.product.update.ProductUpdateRequestDto;
import com.example.productmicroservice.entity.Product;
import com.example.productmicroservice.events.ProductBuyEvent;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This interface represents a service for managing Product entities.
 */
@Service
public interface ProductService {
    /**
     * Creates a new Product.
     *
     * @param product The Product object to create.
     */
    void createProduct(Product product);

    /**
     * Deletes a Product by its id.
     *
     * @param id The id of the Product to delete.
     */
    void deleteProduct(Long id);

    /**
     * Updates a Product with new information.
     *
     * @param id                        The id of the Product to update.
     * @param productUpdateRequestDto   The ProductUpdateRequestDto containing the updated information.
     * @return The updated Product object.
     */
    Product changeProduct(Long id,
                          ProductUpdateRequestDto productUpdateRequestDto);

    /**
     * Retrieves all Products with pagination.
     *
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> getAllProducts(int page,
                                           int size,
                                           String sortBy);

    /**
     * Searches for Products by name with pagination.
     *
     * @param name   The name to search for.
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> searchProductsByName(String name,
                                                    int page,
                                                    int size,
                                                    String sortBy);

    /**
     * Searches for Products by cost with pagination.
     *
     * @param cost   The cost to search for.
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> searchProductsByCost(BigDecimal cost,
                                                    int page,
                                                    int size,
                                                    String sortBy);

    /**
     * Searches for Products by typeId with pagination.
     *
     * @param typeId The typeId to search for.
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> searchProductsByTypeId(Long typeId,
                                                    int page,
                                                    int size,
                                                    String sortBy);

    /**
     * Searches for Products by addDate with pagination.
     *
     * @param addDate   The addDate to search for.
     * @param page      The page number.
     * @param size      The number of items per page.
     * @param sortBy    The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> searchProductsByAddDate(LocalDate addDate,
                                                       int page,
                                                       int size,
                                                       String sortBy);

    /**
     * Searches for Products by customerId with pagination.
     *
     * @param customerId    The customerId to search for.
     * @param page          The page number.
     * @param size          The number of items per page.
     * @param sortBy        The field to sort the results by.
     * @return A Page containing the ProductGetResponseDto objects.
     */
    Page<ProductGetResponseDto> searchProductsByCustomerId(Long customerId,
                                                       int page,
                                                       int size,
                                                       String sortBy);

    /**
     * Processes a ProductBuyEvent for buying a product.
     *
     * @param productBuyEvent The ProductBuyEvent representing the product buy event.
     */
    void buyProduct(ProductBuyEvent productBuyEvent);
}
