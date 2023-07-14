package com.example.productmicroservice.mapper;

import com.example.productmicroservice.dto.type.create.TypeCreateRequestDto;
import com.example.productmicroservice.dto.type.get.TypeGetResponseDto;
import com.example.productmicroservice.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This interface represents a mapper for converting between Type objects and DTOs.
 */
@Mapper
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    Type toType(TypeCreateRequestDto typeCreateRequestDto);

    TypeGetResponseDto toDtoResponse(Type type);

}
