package com.example.productmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


/**
 * The Type entity class represents a type entity in the system.
 * It contains information about a type, such as ID, name.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Type type = (Type) o;
        return getId() != null && Objects.equals(getId(), type.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
