package com.personal.co.ke.phonebook.controller;

import com.personal.co.ke.phonebook.model.Contact;
import com.personal.co.ke.phonebook.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactController {
    private final ContactService contactService;
    @GetMapping
    public List<Contact> getAllContacts(@RequestParam(required = false)String searchTerm,
                                        @RequestParam(required = false)String sort){
        return contactService.getAll(searchTerm, sort);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable String id){
         contactService.deleteContact(Long.parseLong(id));
    }

    @GetMapping("/{id}")
    public Contact getById(@PathVariable String id){
        return contactService.getById(Long.parseLong(id));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Contact addContact(@RequestPart("contract") Contact contact,
                              @RequestPart(value = "imageFile", required = false) MultipartFile imageFile){
        return contactService.addContact(contact, imageFile);
    }
}
