package com.example.customermicroservice.repository;

import com.example.customermicroservice.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing and managing Customer entities in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Retrieves a list of customers who have their birthday today.
     *
     * @return the list of customers with a birthday today
     */
    @Query("SELECT c FROM Customer c WHERE DAY(c.dateOfBirth) = "
            + "DAY(CURRENT_DATE) AND MONTH(c.dateOfBirth) = MONTH(CURRENT_DATE)")
    List<Customer> findCustomersWithBirthdayToday();

    /**
     * Checks if a customer with the specified passport exists in the database.
     *
     * @param passport the passport number to check
     * @return true if a customer with the passport exists, false otherwise
     */
    boolean existsByPassport(String passport);

    /**
     * Finds a customer by the specified passport number.
     *
     * @param passport the passport number to search for
     * @return an optional containing the found customer, or empty if not found
     */
    Optional<Customer> findCustomerByPassport(String passport);

    /**
     * Deletes a customer with the specified passport number.
     *
     * @param passport the passport number of the customer to delete
     */
    void deleteByPassport(String passport);

    /**
     * Retrieves a page of customers whose first name contains the specified value.
     *
     * @param firstName the value to search for in the first name
     * @param pageable  the pageable information for pagination
     * @return a page of customers matching the search criteria
     */
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);

    /**
     * Retrieves a page of customers whose last name contains the specified value.
     *
     * @param lastName  the value to search for in the last name
     * @param pageable  the pageable information for pagination
     * @return a page of customers matching the search criteria
     */
    Page<Customer> findByLastNameContaining(String lastName, Pageable pageable);

    /**
     * Retrieves a page of customers whose passport contains the specified value.
     *
     * @param passport  the value to search for in the passport
     * @param pageable  the pageable information for pagination
     * @return a page of customers matching the search criteria
     */
    Page<Customer> findByPassportContaining(String passport, Pageable pageable);

    /**
     * Retrieves a page of customers with the specified productId.
     *
     * @param productId the value to search for in the productId
     * @param pageable   the pageable information for pagination
     * @return a page of dris matching the search criteria
     */
    Page<Customer> findByProductId(Long productId, Pageable pageable);
}
