package br.com.herlandio7.ekantestspringbootapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DocumentDTOTest {

    @Test
    void testDocumentDTO_WithParameters() {        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();
        String documentType = "Passport";
        String description = "User passport document";

        DocumentDTO documentDTO = new DocumentDTO(dateOfInclusion, dateOfUpdate, documentType, description);

        assertEquals(dateOfInclusion, documentDTO.getDateOfInclusion());
        assertEquals(dateOfUpdate, documentDTO.getDateOfUpdate());
        assertEquals(documentType, documentDTO.getDocumentType());
        assertEquals(description, documentDTO.getDescription());
    }

    @Test
    void testDocumentDTO_WithoutParameters() {
        DocumentDTO documentDTO = new DocumentDTO();

        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();
        String documentType = "ID Card";
        String description = "User ID card document";

        documentDTO.setDateOfInclusion(dateOfInclusion);
        documentDTO.setDateOfUpdate(dateOfUpdate);
        documentDTO.setDocumentType(documentType);
        documentDTO.setDescription(description);

        assertEquals(dateOfInclusion, documentDTO.getDateOfInclusion());
        assertEquals(dateOfUpdate, documentDTO.getDateOfUpdate());
        assertEquals(documentType, documentDTO.getDocumentType());
        assertEquals(description, documentDTO.getDescription());
    }
}

