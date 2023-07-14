package com.example.customermicroservice.service;

import com.example.customermicroservice.dto.customer.create.CustomerCreateRequestDto;
import com.example.customermicroservice.dto.customer.get.CustomerGetResponseDto;
import com.example.customermicroservice.dto.customer.update.CustomerUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service interface for managing customer operations.
 */
@Service
public interface CustomerService {
    /**
     * Congratulates the customers who have their birthday today.
     */
    void congratulateCustomersWithBirthday();

    /**
     * Registers a new customer based on the provided customer request DTO.
     *
     * @param customerRequestDto the driver request DTO
     */
    void registerCustomer(CustomerCreateRequestDto customerRequestDto);

    /**
     * Searches for customers by first name and returns a paginated result.
     *
     * @param firstName the first name to search for
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @return a page of customer response DTOs matching the search criteria
     */
    Page<CustomerGetResponseDto> searchCustomersByFirstName(String firstName,
                                                          int page,
                                                          int size,
                                                          String sortBy);

    /**
     * Searches for customers by experience and returns a paginated result.
     *
     * @param productId the productId to search for
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @return a page of customer response DTOs matching the search criteria
     */
    Page<CustomerGetResponseDto> searchCustomersByProductId(Long productId,
                                                         int page,
                                                         int size,
                                                         String sortBy);

    /**
     * Searches for customers by passport and returns a paginated result.
     *
     * @param passport the passport to search for
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @return a page of customer response DTOs matching the search criteria
     */
    Page<CustomerGetResponseDto> searchCustomersByPassport(String passport,
                                                       int page,
                                                       int size,
                                                       String sortBy);

    /**
     * Searches for customers by last name and returns a paginated result.
     *
     * @param lastName the last name to search for
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @return a page of customer response DTOs matching the search criteria
     */
    Page<CustomerGetResponseDto> searchCustomersByLastName(String lastName,
                                                       int page,
                                                       int size,
                                                       String sortBy);

    /**
     * Retrieves all customers in a paginated result.
     *
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @return a page of customer response DTOs
     */
    Page<CustomerGetResponseDto> getAllCustomers(int page, int size, String sortBy);

    /**
     * Finds a customer by passport number.
     *
     * @param passport the passport number to search for
     * @return an optional containing the found customer response DTO, or empty if not found
     */
    Optional<CustomerGetResponseDto> findCustomerByPassport(String passport);

    /**
     * Deletes a customer with the specified passport number.
     *
     * @param passport the passport number of the customer to delete
     */
    void deleteCustomer(String passport);

    /**
     * Updates the information of a customer with the specified passport number.
     *
     * @param passport the passport number of the driver to update
     * @param updatedCustomer the updated customer request DTO
     */
    void updateCustomer(String passport, CustomerUpdateRequestDto updatedCustomer);

}
