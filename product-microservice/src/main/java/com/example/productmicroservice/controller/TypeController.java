package com.example.productmicroservice.controller;

import com.example.productmicroservice.controller.utils.ControllerUtils;
import com.example.productmicroservice.dto.message.MessageDto;
import com.example.productmicroservice.dto.type.create.TypeCreateRequestDto;
import com.example.productmicroservice.dto.type.get.TypeGetResponseDto;
import com.example.productmicroservice.dto.type.update.TypeUpdateRequestDto;
import com.example.productmicroservice.entity.Type;
import com.example.productmicroservice.mapper.TypeMapper;
import com.example.productmicroservice.service.TypeService;
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

@OpenAPIDefinition(
        info = @Info(
                title = "Simple test",
                version = "1.0.0",
                description = "Service for working with types"
        ),
        servers = @Server(url = "http://localhost:9099")
)
@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    private final ControllerUtils controllerUtils;

    @Autowired
    public TypeController(TypeService typeService, ControllerUtils controllerUtils) {
        this.typeService = typeService;
        this.controllerUtils = controllerUtils;
    }

    @Operation(
            summary = "Create type",
            description = "This endpoint allows to create type",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Type created",
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
    public ResponseEntity<MessageDto> createDetail(@Valid @RequestBody TypeCreateRequestDto typeRequestDto) {
        Type type = TypeMapper.INSTANCE.toType(typeRequestDto);
        typeService.createType(type);
        return controllerUtils.createResponseEntityOk("create.type.message");
    }

    @Operation(
            summary = "Get type by name, with page, filter and sorting",
            description = "This endpoint allows to get type by parameter with page, filter and sorting",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Types successfully found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TypeGetResponseDto.class)
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
    public ResponseEntity<Page<TypeGetResponseDto>> searchTypes(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<TypeGetResponseDto> types;
        if (name != null) {
            types = typeService
                    .searchTypesByName(name, page, size, sortBy);
        } else {
            types = typeService.getAllTypes(page, size, sortBy);
        }
        return ResponseEntity.ok(types);
    }

    @Operation(
            summary = "Delete type by id",
            description = "This endpoint allows to delete type by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Types successfully deleted",
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
        typeService.deleteType(id);
        return controllerUtils.createResponseEntityOk("delete.type.message");
    }

    @Operation(
            summary = "Update type by id",
            description = "This endpoint allows to update type by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Type successfully update",
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
    public ResponseEntity<MessageDto> updateType(@PathVariable Long id,
                                                 @Valid @RequestBody TypeUpdateRequestDto updatedType) {
        typeService.changeType(id, updatedType);
        return controllerUtils.createResponseEntityOk("update.type.message");
    }

}
