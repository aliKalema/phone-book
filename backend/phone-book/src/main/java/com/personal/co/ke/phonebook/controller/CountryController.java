package com.personal.co.ke.phonebook.controller;

import com.personal.co.ke.phonebook.model.Country;
import com.personal.co.ke.phonebook.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountryController {

    private final CountryService countryService;
    @GetMapping
    public List<Country> getAll(){
        return countryService.getAll();
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country){
        return countryService.addCountry(country);
    }
}
