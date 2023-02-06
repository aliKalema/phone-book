package com.personal.co.ke.phonebook.repository;

import com.personal.co.ke.phonebook.model.Contact;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAll(Sort sort);
    @Query("SELECT c FROM Contact c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.otherNames) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.phone) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%',:searchTerm,'%')) " +
            "ORDER BY c.firstName ASC")
    List<Contact> findBySearchTermAndSortAsc(String searchTerm);

    @Query("SELECT c FROM Contact c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.otherNames) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.phone) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%',:searchTerm,'%')) " +
            "ORDER BY c.firstName DESC")
    List<Contact> findBySearchTermAndSortDesc(String searchTerm);

    @Query("SELECT c FROM Contact c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.otherNames) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.phone) LIKE LOWER(CONCAT('%',:searchTerm,'%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%',:searchTerm,'%')) ")
    List<Contact> findBySearchTerm(String searchTerm);

    List<Contact> findAllByOrderByFirstNameAsc();

    List<Contact> findAllByOrderByFirstNameDesc();

    Optional<Contact> findById(Long id);
}
