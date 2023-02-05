package com.personal.co.ke.phonebook.repository;

import com.personal.co.ke.phonebook.model.Contact;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findAll(Sort sort);
    @Query(value = "select  * From contact", nativeQuery = true)
    List<Contact> findByFilter(String searchTerm);

    Optional<Contact> findById(Long id);
}
