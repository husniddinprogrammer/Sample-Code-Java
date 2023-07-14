package com.example.productmicroservice.repository;

import com.example.productmicroservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * This interface represents a repository for Product entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Checks if a Product entity exists with the specified name.
     *
     * @param name The name to check.
     * @return true if a Product entity with the specified name exists, false otherwise.
     */
    boolean existsByName(String name);

    /**
     * Retrieves a Product entity by its name.
     *
     * @param name The name of the Product.
     * @return An Optional containing the Product entity if found, or an empty Optional if not found.
     */
    Optional<Product> findProductByName(String name);

    /**
     * Retrieves Product entities with pagination where the name contains the specified value.
     *
     * @param name      The value to search for in the name field.
     * @param pageable  The pagination information.
     * @return A Page containing the matching Product entities.
     */
    Page<Product> findByName(String name, Pageable pageable);

    /**
     * Retrieves Product entities with pagination where the name contains the specified value.
     *
     * @param cost      The value to search for in the cost field.
     * @param pageable  The pagination information.
     * @return A Page containing the matching Product entities.
     */
    Page<Product> findByCost(BigDecimal cost, Pageable pageable);

    /**
     * Retrieves Product entities with pagination where the typeId contains the specified value.
     *
     * @param typeId    The value to search for in the typeID field.
     * @param pageable  The pagination information.
     * @return A Page containing the matching Product entities.
     */
    Page<Product> findByTypeId(Long typeId, Pageable pageable);

    /**
     * Retrieves Product entities with pagination where the date contains the specified value.
     *
     * @param date      The value to search for in the date field.
     * @param pageable  The pagination information.
     * @return A Page containing the matching Product entities.
     */
    Page<Product> findByAddDate(LocalDate date, Pageable pageable);

    /**
     * Retrieves Product entities with pagination where the customerId contains the specified value.
     *
     * @param customerId    The value to search for in the customerId field.
     * @param pageable      The pagination information.
     * @return A Page containing the matching Product entities.
     */
    Page<Product> findByCustomerId(Long customerId, Pageable pageable);

}
