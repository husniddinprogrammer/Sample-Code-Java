package com.example.customermicroservice.mapper;

import com.example.customermicroservice.dto.customer.create.CustomerCreateRequestDto;
import com.example.customermicroservice.dto.customer.get.CustomerGetResponseDto;
import com.example.customermicroservice.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This interface represents a mapper for converting between Customer objects and DTOs.
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerCreateRequestDto toDtoRequest(Customer customer);

    Customer toCustomer(CustomerCreateRequestDto customerCreateRequestDto);

    CustomerGetResponseDto toDtoResponse(Customer customer);

}
