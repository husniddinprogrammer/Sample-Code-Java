package com.example.productmicroservice.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object (DTO) for a message response.
 * This class represents the payload for a message response from an operation.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto {
  @Schema(description = "Message after operation",
          example = "Some message")
  private String message;
}
