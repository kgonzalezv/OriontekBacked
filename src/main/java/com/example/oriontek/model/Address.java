package com.example.oriontek.model;

import com.example.oriontek.domain.DataCreateAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Table(name = "ADDRESS")
@Entity
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

public Address(DataCreateAddress dataCreateAddress){
    this.street = dataCreateAddress.street();
    this.city = dataCreateAddress.city();
    this.state = dataCreateAddress.state();
    this.zip = dataCreateAddress.zipCode();
}


    @PrePersist
    private void setCreateAt() {
        this.createdAt = LocalDate.now();
    }

}
