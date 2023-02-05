package com.personal.co.ke.phonebook.repository;

import com.personal.co.ke.phonebook.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

}
