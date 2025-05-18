package com.example.oriontek.model;


import com.example.oriontek.domain.DataCreateCustomer;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "CUSTOMER")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Description("name of the customer")
    @Column(nullable = false)
    private String name;

    @Description("email of the customer")
    private String email;

    @Description("phone number of the customer")
    private String phone;

    private LocalDate createdAt;

    public Customer(DataCreateCustomer dataCreateCustomer) {
        this.name = dataCreateCustomer.name();
        this.email = dataCreateCustomer.email();
        this.phone = dataCreateCustomer.phone();
    }


    @PrePersist
    private void setCreateAt() {
        this.createdAt = LocalDate.now();
    }

}
