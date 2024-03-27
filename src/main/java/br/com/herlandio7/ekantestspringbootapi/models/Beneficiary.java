package br.com.herlandio7.ekantestspringbootapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDateTime dateOfInclusion;
    private LocalDateTime dateOfUpdate;

    @OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL)
    private List<Document> documents;

    public Beneficiary() {
    }

    public Beneficiary(String username, String phoneNumber, LocalDate dateOfBirth, LocalDateTime dateOfInclusion,
            LocalDateTime dateOfUpdate) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfUpdate = dateOfUpdate;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
