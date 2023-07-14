package com.example.productmicroservice.dto.type.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object (DTO) for updating types.
 * This class represents the request payload for updating types.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeUpdateRequestDto {
    private Long id;
    @Schema(description = "Type's name",
            example = "food")
    @NotBlank(message = "{validate.not-blank}")
    private String name;
}
