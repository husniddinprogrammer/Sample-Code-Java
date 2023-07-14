package com.example.customermicroservice.service.implementation;

import com.example.customermicroservice.dto.customer.create.CustomerCreateRequestDto;
import com.example.customermicroservice.dto.customer.get.CustomerGetResponseDto;
import com.example.customermicroservice.dto.customer.update.CustomerUpdateRequestDto;
import com.example.customermicroservice.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Override
    public void congratulateCustomersWithBirthday() {

    }

    @Override
    public void registerCustomer(CustomerCreateRequestDto customerRequestDto) {

    }

    @Override
    public Page<CustomerGetResponseDto> searchCustomersByFirstName(String firstName, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<CustomerGetResponseDto> searchCustomersByProductId(Long productId, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<CustomerGetResponseDto> searchCustomersByPassport(String passport, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<CustomerGetResponseDto> searchCustomersByLastName(String lastName, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Page<CustomerGetResponseDto> getAllCustomers(int page, int size, String sortBy) {
        return null;
    }

    @Override
    public Optional<CustomerGetResponseDto> findCustomerByPassport(String passport) {
        return Optional.empty();
    }

    @Override
    public void deleteCustomer(String passport) {

    }

    @Override
    public void updateCustomer(String passport, CustomerUpdateRequestDto updatedCustomer) {

    }
}
