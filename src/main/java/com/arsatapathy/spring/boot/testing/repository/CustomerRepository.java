package com.arsatapathy.spring.boot.testing.repository;

import com.arsatapathy.spring.boot.testing.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
