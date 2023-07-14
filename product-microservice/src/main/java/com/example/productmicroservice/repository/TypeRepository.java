package com.example.productmicroservice.repository;

import com.example.productmicroservice.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for Type entities.
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    /**
     * Checks if a Type entity exists with the specified name.
     *
     * @param name The name to check.
     * @return true if a Type entity with the specified name exists, false otherwise.
     */
    boolean existsByName(String name);
    /**
     * Retrieves Type entities with pagination where the name contains the specified value.
     *
     * @param name      The value to search for in the name field.
     * @param pageable  The pagination information.
     * @return A Page containing the matching Type entities.
     */
    Page<Type> findByName(String name, Pageable pageable);
}
