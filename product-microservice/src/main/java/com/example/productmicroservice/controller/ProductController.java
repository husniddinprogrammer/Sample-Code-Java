package com.example.productmicroservice.controller;

import com.example.productmicroservice.controller.utils.ControllerUtils;
import com.example.productmicroservice.dto.message.MessageDto;
import com.example.productmicroservice.dto.product.create.ProductCreateRequestDto;
import com.example.productmicroservice.dto.product.get.ProductGetResponseDto;
import com.example.productmicroservice.dto.product.update.ProductUpdateRequestDto;
import com.example.productmicroservice.entity.Product;
import com.example.productmicroservice.mapper.ProductMapper;
import com.example.productmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@OpenAPIDefinition(
        info = @Info(
                title = "Simple test",
                version = "1.0.0",
                description = "Service for working with products"
        ),
        servers = @Server(url = "http://localhost:9099")
)
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ControllerUtils controllerUtils;

    @Autowired
    public ProductController(ProductService productService, ControllerUtils controllerUtils) {
        this.productService = productService;
        this.controllerUtils = controllerUtils;
    }

    @Operation(
            summary = "Create product",
            description = "This endpoint allows to create product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Already exists",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<MessageDto> createProduct(@Valid @RequestBody ProductCreateRequestDto productRequestDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequestDto);
        productService.createProduct(product);
        return controllerUtils.createResponseEntityOk("create.product.message");
    }

    @Operation(
            summary = "Get product by name, cost, typeId , addDate, customerId, with page, filter and sorting",
            description = "This endpoint allows to get product by parameter with page, filter and sorting",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductGetResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<Page<ProductGetResponseDto>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal cost,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) LocalDate addDate,
            @RequestParam(required = false) Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        if (name != null) {
            Page<ProductGetResponseDto> products = productService
                    .searchProductsByName(name, page, size, sortBy);
            return ResponseEntity.ok(products);
        } else if (cost != null) {
            Page<ProductGetResponseDto> products = productService
                    .searchProductsByCost(cost, page, size, sortBy);
            return ResponseEntity.ok(products);
        } else if (typeId != null) {
            Page<ProductGetResponseDto> products = productService
                    .searchProductsByTypeId(typeId, page, size, sortBy);
            return ResponseEntity.ok(products);
        } else if (addDate != null) {
            Page<ProductGetResponseDto> products = productService
                    .searchProductsByAddDate(addDate, page, size, sortBy);
            return ResponseEntity.ok(products);
        } else if (customerId != null) {
            Page<ProductGetResponseDto> products = productService
                    .searchProductsByCustomerId(customerId, page, size, sortBy);
            return ResponseEntity.ok(products);
        } else {
            Page<ProductGetResponseDto> products = productService.getAllProducts(page, size, sortBy);
            return ResponseEntity.ok(products);
        }
    }

    @Operation(
            summary = "Delete product by id",
            description = "This endpoint allows to delete product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully deleted",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteDetail(@PathVariable Long id) {
        productService.deleteProduct(id);
        return controllerUtils.createResponseEntityOk("delete.product.message");
    }

    @Operation(
            summary = "Update product by id",
            description = "This endpoint allows to update product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully update",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
                            )
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<MessageDto> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody ProductUpdateRequestDto updatedProduct) {
        productService.changeProduct(id, updatedProduct);
        return controllerUtils.createResponseEntityOk("update.product.message");
    }

}
