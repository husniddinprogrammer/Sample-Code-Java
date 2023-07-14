package com.example.productmicroservice.mapper;

import com.example.productmicroservice.dto.product.create.ProductCreateRequestDto;
import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This interface represents a mapper for converting between Product objects and DTOs.
 */
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductCreateRequestDto productCreateRequestDto);

    ProductGetResponseDto toDtoResponse(Product product);

}
