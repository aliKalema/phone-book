package com.personal.co.ke.phonebook.service;

import com.personal.co.ke.phonebook.exception.ContactNotFoundException;
import com.personal.co.ke.phonebook.model.Contact;
import com.personal.co.ke.phonebook.model.Country;
import com.personal.co.ke.phonebook.repository.ContactRepository;
import com.personal.co.ke.phonebook.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {
    private final CountryRepository countryRepository;
    private final ContactRepository contactRepository;

    public List<Contact> getAll(String searchTerm, String sort) {
        if(searchTerm.isEmpty()){
            return contactRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        }
        return contactRepository.findByFilter(searchTerm);
    }

    public Contact getById(long id) {
        return contactRepository.findById(id).orElseThrow(()->new ContactNotFoundException(String.valueOf(id)));
    }

    public Contact addContact(Contact contact, MultipartFile multipartFile) {
        if(multipartFile != null){
            try{
                String extension = com.google.common.io.Files.getFileExtension(multipartFile.getOriginalFilename());
                String fileName = new StringBuilder().append(UUID.randomUUID().toString() + "-" + multipartFile.getOriginalFilename()).append(String.valueOf(multipartFile.getSize())).append(".").append(extension).toString();
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
                Files.write(path, bytes);
                contact.setImage(fileName);
            }
            catch (IOException e) {
                log.info("Unable to upload image");
            }
        }
        Country country = countryRepository.findById(Long.valueOf(contact.getCountryId())).get();
        contact.setCountry(country);
        return  contactRepository.save(contact);
    }

    private Sort getSort(String sort){
        return sort.equals("desc") ? Sort.by(Sort.Direction.DESC, "firstName") : Sort.by(Sort.Direction.ASC, "firstName");
    }
}
