package com.example.oriontek.repository;

import com.example.oriontek.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long>{
    List<Address> findAddressesByCustomerId(Long customerId);
    void deleteAddressByCustomerId(Long customerId);
}
