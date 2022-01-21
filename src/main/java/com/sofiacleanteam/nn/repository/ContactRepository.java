package com.sofiacleanteam.nn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofiacleanteam.nn.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
