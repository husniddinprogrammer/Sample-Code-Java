package com.example.productmicroservice.dto.type.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object (DTO) for creating a type.
 * This class represents the request payload for creating a type.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TypeCreateRequestDto {
    private Long id;
    @Schema(description = "Type's name",
    example = "food")
    @NotBlank(message = "{validate.not-blank}")
    private String name;
}
