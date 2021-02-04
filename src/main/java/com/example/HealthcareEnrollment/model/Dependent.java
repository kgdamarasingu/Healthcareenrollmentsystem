package com.example.HealthcareEnrollment.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "DEPENDENT")
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long depId;

    @Column(name = "depName")
    @NotEmpty(message = "depName  cannot  be  empty")
    private String depName;

    @Column(name = "depDob")
    private String depDob;

    @Column(name = "status")
    private boolean status;

    @Column(name = "relationship")
    @NotEmpty(message = "relationship  cannot  be  empty")
    private String relationship;

    @Column(name = "parentId")
    private long parentId;

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepDob() {
        return depDob;
    }

    public void setDepDob(String depDob) {
        this.depDob = depDob;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

}
