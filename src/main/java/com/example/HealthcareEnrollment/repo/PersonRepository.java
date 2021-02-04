package com.example.HealthcareEnrollment.repo;

import com.example.HealthcareEnrollment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByNameIgnoreCase(String name);

    @Modifying
    @Query("update Person set name=:name, dob=:dob, phone=:phone, activation= :activation where name = :name")
    Person updateByName(@Param("name") String name, @Param("dob") String dob, @Param("phone") String phone, @Param("activation") boolean activation);

}