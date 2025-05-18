package com.example.oriontek.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "ADDRESS")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String zip;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ADDRESS_CUSTOMER"), referencedColumnName = "id")
    private Customer customer;

    private LocalDate createdAt;

    @PrePersist
    private void setCreateAt() {
        this.createdAt = LocalDate.now();
    }
}
