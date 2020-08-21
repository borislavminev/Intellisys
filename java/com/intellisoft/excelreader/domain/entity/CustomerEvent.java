package com.intellisoft.excelreader.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerEvent {

    private Long id;
    private String companyName;
    private Date dateOfCreation;
    private String email;
    private String family;
    private String firstName;
    private Integer numberOfParticipant;
    private String phone;


    public CustomerEvent() {
    }

    public CustomerEvent(String companyName, Date dateOfCreation, String email, String family, String firstName, Integer numberOfParticipant, String phone) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "family")
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "participant_count")
    public Integer getNumberOfParticipant() {
        return numberOfParticipant;
    }

    public void setNumberOfParticipant(Integer numberOfParticipant) {
        this.numberOfParticipant = numberOfParticipant;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="registration_date")
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
