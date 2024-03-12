package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;

public class DocumentDTO {

    private Beneficiary beneficiaryId;
    private LocalDateTime dateOfInclusion;
    private LocalDateTime dateOfUpdate;
    private String documentType;
    private String description;

    public DocumentDTO() {
    }

    public DocumentDTO(Beneficiary beneficiaryId, LocalDate dateOfBirth, LocalDateTime dateOfInclusion,
            LocalDateTime dateOfUpdate, String documentType, String description) {
        this.beneficiaryId = beneficiaryId;
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfUpdate = dateOfUpdate;
        this.documentType = documentType;
        this.description = description;
    }

    public Beneficiary getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Beneficiary beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
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

}
