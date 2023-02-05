package com.personal.co.ke.phonebook.service;

import com.personal.co.ke.phonebook.model.Country;
import com.personal.co.ke.phonebook.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.personal.co.ke.phonebook.repository.CountryRepository;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final ContactRepository contactRepository;

    public List<Country> getAll(){
        List<Country>countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return countries;
    }

    public Country addCountry(Country country) {
        return  countryRepository.save(country);
    }
}
