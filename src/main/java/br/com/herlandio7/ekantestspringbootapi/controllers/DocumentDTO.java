package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.time.LocalDateTime;

public class DocumentDTO {

    private LocalDateTime dateOfInclusion;
    private LocalDateTime dateOfUpdate;
    private String documentType;
    private String description;

    public DocumentDTO(LocalDateTime dateOfInclusion, LocalDateTime dateOfUpdate, String documentType,
            String description) {
        this.dateOfInclusion = dateOfInclusion;
        this.dateOfUpdate = dateOfUpdate;
        this.documentType = documentType;
        this.description = description;
    }

    public DocumentDTO() {
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
