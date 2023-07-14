package com.example.productmicroservice.service.implementation;

import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.dto.product.update.ProductUpdateRequestDto;
import com.example.productmicroservice.entity.Product;
import com.example.productmicroservice.events.ProductBuyEvent;
import com.example.productmicroservice.exceptions.ErrorCode;
import com.example.productmicroservice.exceptions.product.ProductAlreadyExistException;
import com.example.productmicroservice.exceptions.product.ProductNotFoundException;
import com.example.productmicroservice.kafka.Producer;
import com.example.productmicroservice.mapper.ProductMapper;
import com.example.productmicroservice.repository.ProductRepository;
import com.example.productmicroservice.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final EntityManager entityManager;
    private final Producer producer;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository, EntityManager entityManager, Producer producer) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
        this.producer = producer;
    }

    @Override
    public void createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistException(ErrorCode.PRODUCT_ALREADY_EXIST);
        }
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product changeProduct(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setName(Objects.requireNonNullElse(productUpdateRequestDto.getName(),
                product.getName()));
        product.setCost(Objects.requireNonNullElse(productUpdateRequestDto.getCost(),
                product.getCost()));
        product.setType(Objects.requireNonNullElse(productUpdateRequestDto.getType(),
                product.getType()));
        product.setAddDate(Objects.requireNonNullElse(productUpdateRequestDto.getAddDate(),
                product.getAddDate()));
        product.setCustomerId(Objects.requireNonNullElse(productUpdateRequestDto.getCustomerId(),
                product.getCustomerId()));
        return productRepository.save(product);
    }

    @Override
    public Page<ProductGetResponseDto> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<ProductGetResponseDto> searchProductsByName(String name, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findByName(name, pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<ProductGetResponseDto> searchProductsByCost(BigDecimal cost, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findByCost(cost, pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<ProductGetResponseDto> searchProductsByTypeId(Long typeId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findByTypeId(typeId, pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<ProductGetResponseDto> searchProductsByAddDate(LocalDate addDate, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findByAddDate(addDate, pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Override
    public Page<ProductGetResponseDto> searchProductsByCustomerId(Long customerId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findByCustomerId(customerId, pageable);
        return productPage.map(ProductMapper.INSTANCE::toDtoResponse);
    }

    @Transactional
    @Override
    public void buyProduct(ProductBuyEvent productBuyEvent) {
        if (!productRepository.existsByName(productBuyEvent.getProductName())) {
            throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Optional<Product> optionalProduct = productRepository
                .findProductByName(productBuyEvent.getProductName());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            entityManager.detach(product);
            product.setCustomerId(productBuyEvent.getCustomerId());
            entityManager.merge(product);
            log.info("Product order persisted: {}", productBuyEvent);
        } else {
            throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }
}
