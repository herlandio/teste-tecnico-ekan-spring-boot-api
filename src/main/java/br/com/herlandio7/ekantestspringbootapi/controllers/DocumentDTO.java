package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;

public class DocumentDTO {

    private Long beneficiaryId;
    private Beneficiary beneficiary;
    private LocalDateTime dateOfInclusion;
    private LocalDateTime dateOfUpdate;
    private String documentType;
    private String description;

    public DocumentDTO() {
    }

    public DocumentDTO(Beneficiary beneficiary, LocalDate dateOfBirth, LocalDateTime dateOfInclusion,
            LocalDateTime dateOfUpdate, String documentType, String description) {
        this.beneficiary = beneficiary;
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfUpdate = dateOfUpdate;
        this.documentType = documentType;
        this.description = description;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

}
