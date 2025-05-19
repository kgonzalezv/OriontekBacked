package com.example.oriontek.service;

import com.example.oriontek.domain.DataResponseAddress;
import com.example.oriontek.model.Address;
import com.example.oriontek.model.Customer;
import com.example.oriontek.repository.AddressRepository;
import com.example.oriontek.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;
    CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public void saveAddress(Address address, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    public List<DataResponseAddress> getAllAddressByCustomerId(Long customerId) {
        List<Address> addressList = addressRepository.findAddressesByCustomerId(customerId);
        return addressList.stream().map(address ->
                new DataResponseAddress(address.getStreet(), address.getCity(),
                        address.getState(), address.getZip(), address.getCustomer().getName())).toList();

    }

    public List<DataResponseAddress> getAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(address ->
                new DataResponseAddress(address.getStreet(), address.getCity(),
                        address.getState(), address.getZip(), address.getCustomer().getName())).toList();

    }

    public void deleteAddressByCustomerId(Long id) {
        addressRepository.deleteAddressByCustomerId(id);
    }


}
