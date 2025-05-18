package com.example.oriontek.service;

import com.example.oriontek.domain.DataCreateCustomer;
import com.example.oriontek.domain.DataResponseCustomer;
import com.example.oriontek.model.Customer;
import com.example.oriontek.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository  customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public void saveCustomer(Customer customer){
     customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id).isPresent() ? customerRepository.findById(id) : Optional.empty();
    }

    public List<DataResponseCustomer> getAllCustomers(){
       List<Customer> customers =  customerRepository.findAll();
       return customers.stream().map(customer ->
               new DataResponseCustomer(customer.getName(), customer.getEmail(),
                       customer.getPhone(), customer.getCreatedAt().toString())).toList();
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomers(){
        customerRepository.deleteAll();
    }

    public DataCreateCustomer updateCustomerById(DataCreateCustomer customer, Long id){
        Customer customerToUpdate = getCustomerById(id).orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        customerToUpdate.setName(customer.name());
        customerToUpdate.setEmail(customer.email());
        customerToUpdate.setPhone(customer.phone());
        customerRepository.save(customerToUpdate);
        return customer;
    }

    }


