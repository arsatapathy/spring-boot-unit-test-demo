package com.arsatapathy.spring.boot.testing.service;

import com.arsatapathy.spring.boot.testing.model.Customer;
import com.arsatapathy.spring.boot.testing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> customers() {
        List<Customer> customers = new LinkedList<>();

        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
