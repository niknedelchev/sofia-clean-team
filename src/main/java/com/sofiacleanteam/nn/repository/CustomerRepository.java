package com.sofiacleanteam.nn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofiacleanteam.nn.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
