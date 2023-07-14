package com.example.productmicroservice.service.implementation;

import com.example.productmicroservice.dto.type.get.TypeGetResponseDto;
import com.example.productmicroservice.dto.type.update.TypeUpdateRequestDto;
import com.example.productmicroservice.entity.Type;
import com.example.productmicroservice.exceptions.ErrorCode;
import com.example.productmicroservice.exceptions.type.TypeAlreadyExistException;
import com.example.productmicroservice.exceptions.type.TypeNotFoundException;
import com.example.productmicroservice.mapper.TypeMapper;
import com.example.productmicroservice.repository.TypeRepository;
import com.example.productmicroservice.service.TypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TypeServiceImplementation implements TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImplementation(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void createType(Type type) {
        if (typeRepository.existsByName(type.getName())) {
            throw new TypeAlreadyExistException(ErrorCode.TYPE_ALREADY_EXIST);
        }
        typeRepository.save(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new TypeAlreadyExistException(ErrorCode.TYPE_ALREADY_EXIST);
        }
        typeRepository.deleteById(id);
    }

    @Override
    public Type changeType(Long id, TypeUpdateRequestDto typeUpdateRequestDto) {
        if (!typeRepository.existsById(id)) {
            throw new TypeNotFoundException(ErrorCode.TYPE_NOT_FOUND);
        }
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new TypeNotFoundException(ErrorCode.TYPE_NOT_FOUND));
        type.setName(Objects.requireNonNullElse(typeUpdateRequestDto.getName(),
                type.getName()));
        return typeRepository.save(type);
    }

    @Override
    public Page<TypeGetResponseDto> getAllTypes(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Type> typePage = typeRepository.findAll(pageable);
        return typePage.map(TypeMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<TypeGetResponseDto> searchTypesByName(String name, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Type> typePage = typeRepository.findByName(name,pageable);
        return typePage.map(TypeMapper.INSTANCE::toDtoResponse);
    }
}
