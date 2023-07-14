package com.example.productmicroservice.dto.product.update;

import com.example.productmicroservice.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for updating products.
 * This class represents the request payload for updating products.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductUpdateRequestDto {
    private Long id;
    @Schema(description = "Product's name",
            example = "HP notebook")
    @NotBlank(message = "{validate.not-blank}")
    private String name;
    @Schema(description = "Product's cost",
            example = "20")
    @NotNull(message = "{validate.not-null}")
    private BigDecimal cost;
    @Schema(description = "Product's type")
    private Type type;
    @Schema(description = "Product's addDate",
            example = "12.01.2023")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate addDate;
    @Schema(description = "Product's customer ID",
            example = "4")
    private Long customerId;
}
