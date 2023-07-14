package com.example.customermicroservice.controller;

import com.example.customermicroservice.controller.utils.ControllerUtils;
import com.example.customermicroservice.dto.customer.create.CustomerCreateRequestDto;
import com.example.customermicroservice.dto.customer.get.CustomerGetResponseDto;
import com.example.customermicroservice.dto.customer.update.CustomerUpdateRequestDto;
import com.example.customermicroservice.dto.message.MessageDto;
import com.example.customermicroservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final ControllerUtils controllerUtils;

    @Autowired
    public CustomerController(CustomerService customerService, ControllerUtils controllerUtils) {
        this.customerService = customerService;
        this.controllerUtils = controllerUtils;
    }

    @Operation(
            summary = "Endpoint for create customer",
            description = "This endpoint allows to create customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created",
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
    @PostMapping("/")
    public ResponseEntity<MessageDto> registerCustomer(
            @Valid @RequestBody CustomerCreateRequestDto customerCreateRequestDto) {
        customerService.registerCustomer(customerCreateRequestDto);
        return controllerUtils.createResponseEntityOk("create.customer.message");
    }

    @Operation(
            summary = "Endpoint for get customer by passport",
            description = "This endpoint allows to get method for customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get Customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerGetResponseDto.class)
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
    @GetMapping("/{passport}")
    public ResponseEntity<CustomerGetResponseDto> findCustomerByPassport(@PathVariable String passport) {
        Optional<CustomerGetResponseDto> customer = customerService.findCustomerByPassport(passport);
        return ResponseEntity.ok(customer.get());
    }

    @Operation(
            summary = "Endpoint for delete customer by passport",
            description = "This endpoint allows to delete method for customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
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
    @DeleteMapping("/{passport}")
    public ResponseEntity<MessageDto> deleteCustomerByPassport(@PathVariable String passport) {
        customerService.deleteCustomer(passport);
        return controllerUtils.createResponseEntityOk("delete.customer.message");
    }

    @Operation(
            summary = "Endpoint for update customer by passport",
            description = "This endpoint allows to update method for customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer Update",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageDto.class)
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
    @PatchMapping("/{passport}")
    public ResponseEntity<MessageDto> updateCustomer(@PathVariable String passport,
                                                     @Valid @RequestBody CustomerUpdateRequestDto customerUpdateRequestDto) {
        customerService.updateCustomer(passport, customerUpdateRequestDto);
        return controllerUtils.createResponseEntityOk("update.customer.message");
    }

    @Operation(
            summary = "Get customers",
            description = "This endpoint allows to get customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customers successfully found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerGetResponseDto.class)
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
    @GetMapping
    public ResponseEntity<Page<CustomerGetResponseDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy
    ) {
        Page<CustomerGetResponseDto> customers = customerService.getAllCustomers(page, size, sortBy);
        return ResponseEntity.ok(customers);
    }@Operation(
            summary = "Get customers by firstName, lastName, passport, productId, with page, filter and sorting",
            description = "This endpoint allows to get customers by parameter with page, filter and sorting",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customers successfully found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerGetResponseDto.class)
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
    public ResponseEntity<Page<CustomerGetResponseDto>> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String passport,
            @RequestParam(required = false) Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy) {
        if (firstName != null) {
            Page<CustomerGetResponseDto> customers = customerService
                    .searchCustomersByFirstName(firstName, page, size, sortBy);
            return ResponseEntity.ok(customers);
        } else if (lastName != null) {
            Page<CustomerGetResponseDto> customers = customerService
                    .searchCustomersByPassport(passport, page, size, sortBy);
            return ResponseEntity.ok(customers);
        } else if (productId != null) {
            Page<CustomerGetResponseDto> customers = customerService
                    .searchCustomersByProductId(productId, page, size, sortBy);
            return ResponseEntity.ok(customers);
        } else {
            Page<CustomerGetResponseDto> customers = customerService.getAllCustomers(page, size, sortBy);
            return ResponseEntity.ok(customers);
        }
    }
}
