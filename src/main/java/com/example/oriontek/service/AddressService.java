package com.example.oriontek.service;

import com.example.oriontek.model.Address;
import com.example.oriontek.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService( AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Address address, Long customerId){
        addressRepository.save(address);
    }

    public Optional<Address> getAddressById(Long id){
        return addressRepository.findById(id).isPresent() ? addressRepository.findById(id) : Optional.empty();
    }

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteAddressById(Long id){
        addressRepository.deleteById(id);
    }

    public void deleteAllAddresses(){
        addressRepository.deleteAll();
    }

}
