package com.example.customermicroservice.dto.message;

import lombok.*;

/**
 * Data transfer object representing a message.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto {
  private String message;
}