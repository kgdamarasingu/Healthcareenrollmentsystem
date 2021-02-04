package com.example.HealthcareEnrollment.controller;

import com.example.HealthcareEnrollment.model.Dependent;
import com.example.HealthcareEnrollment.model.Person;
import com.example.HealthcareEnrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    EnrollmentService service;

    @PostMapping("/create")
    public ResponseEntity<Person> createOrUpdateEmployee(@RequestBody @Valid Person person) {
        return new ResponseEntity<>(service.createPerson(person), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/person/name")
    public ResponseEntity<Person> getPerson(@RequestParam @NotEmpty String name) {
        return new ResponseEntity<>(service.getPerson(name), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/dependent/name")
    public ResponseEntity<Set<Dependent>> getDependent(@RequestParam @NotEmpty String name) {
        return new ResponseEntity<>(service.getDependent(name), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/person/dependents/name")
    public ResponseEntity<Set<Dependent>> getDependents(@RequestParam @NotEmpty String name) {
        return new ResponseEntity<>(service.getDependentList(name), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/inactive/person")
    public ResponseEntity<Person> setPersonInactive(@RequestParam @NotEmpty String name) {
        return new ResponseEntity<>(service.disablePerson(name), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/inactive/dependent")
    public ResponseEntity<Dependent> setDependentInactive(@RequestParam @NotEmpty String name, @RequestParam @NotEmpty String personame) {
        return new ResponseEntity<>(service.disableDependent(name, personame), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/name")
    public ResponseEntity<String> deleteEmployee(@RequestParam @NotEmpty String name) {
        service.deletePerson(name);
        return new ResponseEntity<String>("Record deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/modify/{name}")
    public ResponseEntity<String> modifyEmployee(@PathVariable("name") @NotEmpty String name, @RequestBody @Valid Person person) {
        service.modifyPerson(name, person);
        return new ResponseEntity<String>("Record updated successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
