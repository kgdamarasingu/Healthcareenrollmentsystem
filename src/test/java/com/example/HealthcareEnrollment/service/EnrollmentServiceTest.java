package com.example.HealthcareEnrollment.service;

import com.example.HealthcareEnrollment.model.Dependent;
import com.example.HealthcareEnrollment.model.Person;
import com.example.HealthcareEnrollment.repo.DependentRepository;
import com.example.HealthcareEnrollment.repo.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class EnrollmentServiceTest {

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DependentRepository dependentRepository;

    @Test
    public void createPersonTest() {
        Assertions.assertEquals(enrollmentService.createPerson(createPerson("kumari")).getName(), createPerson("kumari").getName());
    }

    @Test
    public void getpersonTest() {
        Assertions.assertEquals(enrollmentService.getPerson("kumari").getName(), createPerson("kumari").getName());
    }

    @Test
    public void getpersonFaliureTest() {
        Assertions.assertNull(enrollmentService.getPerson("kuri"));
    }

    private Person createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        person.setActivation(true);
        person.setDob("07/27/1990");
        person.setPhone("02131578");
        person.setId(1l);
        Set<Dependent> deprecateds = new HashSet<Dependent>();
        deprecateds.add(creatDependend());
        return person;

    }

    private Dependent creatDependend() {

        Dependent dependent = new Dependent();
        dependent.setRelationship("sister");
        dependent.setStatus(true);
        dependent.setDepDob("02081990");
        dependent.setDepId(101l);
        dependent.setDepName("test");
        return dependent;
    }
}
