package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BeneficiaryDTO {

    private String name;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDateTime dateOfInclusion;
    private LocalDateTime dateOfUpdate;

    public BeneficiaryDTO(String name, String phoneNumber, LocalDate dateOfBirth, LocalDateTime dateOfInclusion,
            LocalDateTime dateOfUpdate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfUpdate = dateOfUpdate;
    }
    
    public BeneficiaryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateOfInclusion() {
        return dateOfInclusion;
    }

    public void setDateOfInclusion(LocalDateTime dateOfInclusion) {
        this.dateOfInclusion = dateOfInclusion;
    }

    public LocalDateTime getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }
}
