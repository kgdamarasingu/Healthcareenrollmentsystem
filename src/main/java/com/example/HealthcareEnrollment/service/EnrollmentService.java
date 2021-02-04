package com.example.HealthcareEnrollment.service;

import com.example.HealthcareEnrollment.Exception.ResourceNotFoundException;
import com.example.HealthcareEnrollment.model.Dependent;
import com.example.HealthcareEnrollment.model.Person;
import com.example.HealthcareEnrollment.repo.DependentRepository;
import com.example.HealthcareEnrollment.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DependentRepository dependentRepository;

    public Person createPerson(Person Person) {
        return personRepository.save(Person);
    }

    public Person getPerson(String name) {
        return personRepository.findByNameIgnoreCase(name);
    }

    public Set<Dependent> getDependent(String name) {
        return dependentRepository.findByDepNameIgnoreCase(name);
    }

    public Set<Dependent> getDependentList(String name) {
        Person person = personRepository.findByNameIgnoreCase(name);
        if (person != null && person.getName().equals(name)) {
            return dependentRepository.findById(person.getId());
        } else {
            throw new ResourceNotFoundException("Record not Found with name : " + name);
        }
    }

    public void deletePerson(String name) {
        Person existingPerson = personRepository.findByNameIgnoreCase(name);
        if (existingPerson != null && existingPerson.getName().equals(name)) {
            personRepository.deleteById(existingPerson.getId());
        } else {
            throw new ResourceNotFoundException("Record not Found with name : " + name);
        }
    }

    public void modifyPerson(String name, Person person) {
        Person existingPerson = personRepository.findByNameIgnoreCase(name);
        if (existingPerson != null && existingPerson.getName().equals(name)) {
            Person newPerson = new Person();
            newPerson.setName(person.getName() != null ? person.getName() : existingPerson.getName());
            newPerson.setDob(person.getDob() != null ? person.getDob() : existingPerson.getDob());
            newPerson.setPhone(person.getName() != null ? person.getPhone() : existingPerson.getPhone());
            newPerson.setActivation(person.isActivation() ? person.isActivation() : existingPerson.isActivation());
            personRepository.updateByName(newPerson.getName(), newPerson.getDob(), newPerson.getPhone(), newPerson.isActivation());
        }
    }

    public void modifyDependent(String name, Dependent person) {
        Set<Dependent> existingPerson = dependentRepository.findByDepNameIgnoreCase(name);
        for (Dependent dependent : existingPerson) {
            if (existingPerson != null && dependent.getDepName().equals(name)) {
                Dependent newPerson = new Dependent();
                newPerson.setDepName(person.getDepName() != null ? person.getDepName() : dependent.getDepName());
                newPerson.setDepDob(person.getDepDob() != null ? person.getDepDob() : dependent.getDepDob());
                newPerson.setRelationship(person.getRelationship() != null ? person.getRelationship() : dependent.getRelationship());
                dependentRepository.updateByDepName(newPerson.getDepName(), newPerson.getDepDob(), newPerson.isStatus(), newPerson.getRelationship());
            }
        }
    }

    public void deleteDependent(String name) {
        Set<Dependent> existingPerson = dependentRepository.findByDepNameIgnoreCase(name);
        for (Dependent dependent : existingPerson) {
            if (existingPerson != null && dependent.getDepName().equals(name)) {
                dependentRepository.deleteByDepName(name);
            }
        }
    }

    public Dependent disableDependent(String name, String personName) {
        Person person = personRepository.findByNameIgnoreCase(personName);
        Set<Dependent> existingPerson = dependentRepository.findByDepNameIgnoreCase(name);
        for (Dependent dependent : existingPerson) {
            if (existingPerson != null && dependent.getDepName().equals(name) && dependent.isStatus()) {
                return dependentRepository.updateByDepName(dependent.getDepName(), dependent.getDepDob(), false, dependent.getRelationship());
            }
        }
        return null;
    }


    public Person disablePerson(String name) {
        Person existingPerson = personRepository.findByNameIgnoreCase(name);
        if (existingPerson != null && existingPerson.getName().equals(name) && existingPerson.isActivation()) {
            return personRepository.updateByName(existingPerson.getName(), existingPerson.getDob(), existingPerson.getPhone(), false);
        }
        return null;
    }
}
