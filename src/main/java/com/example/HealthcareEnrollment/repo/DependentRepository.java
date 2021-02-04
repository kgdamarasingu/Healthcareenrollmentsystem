package com.example.HealthcareEnrollment.repo;

import com.example.HealthcareEnrollment.model.Dependent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DependentRepository extends CrudRepository<Dependent, Long> {

    Set<Dependent> findByDepNameIgnoreCase(String name);

    Set<Dependent> deleteByDepName(String name);

    @Modifying
    Set<Dependent> findById(long id);

    @Modifying
    @Query("update Dependent set depName=:name, depDob=:dob, status= :activation,relationship = :relation where depName = :name")
    Dependent updateByDepName(@Param("name") String name, @Param("dob") String dob, @Param("activation") boolean activation, @Param("relation") String relation);

}
