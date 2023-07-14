package com.example.productmicroservice.dto.type.get;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


/**
 * Data Transfer Object (DTO) for retrieving types.
 * This class represents the response payload for retrieving types.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeGetResponseDto {
    private Long id;
    @Schema(description = "Type's name",
            example = "food")
    @NotBlank(message = "${validate.not-blank}")
    private String name;
}
