package com.example.customermicroservice.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Type {
    private Long id;
    private String name;
}
