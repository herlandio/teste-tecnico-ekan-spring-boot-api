package br.com.herlandio7.ekantestspringbootapi.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void testDocumentConstructorAndGetters() {
       
        Beneficiary beneficiary = new Beneficiary("John Doe", "123456789", LocalDate.of(1990, 1, 1), LocalDateTime.now(), LocalDateTime.now());
        String documentType = "Passport";
        String description = "Passport for international travel";
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();
        
        Document document = new Document(beneficiary, documentType, description, dateOfInclusion, dateOfUpdate);
        
        assertEquals(beneficiary, document.getBeneficiary());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(description, document.getDescription());
        assertEquals(dateOfInclusion, document.getDateOfInclusion());
        assertEquals(dateOfUpdate, document.getDateOfUpdate());
    }

    @Test
    void testDocumentSetters() {
        
        Document document = new Document();
        
        Beneficiary beneficiary = new Beneficiary("Jane Doe", "987654321", LocalDate.of(1995, 5, 5), LocalDateTime.now(), LocalDateTime.now());
        String documentType = "Driver's License";
        String description = "Valid for driving in the state";
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();

        document.setBeneficiary(beneficiary);
        document.setDocumentType(documentType);
        document.setDescription(description);
        document.setDateOfInclusion(dateOfInclusion);
        document.setDateOfUpdate(dateOfUpdate);
        
        assertEquals(beneficiary, document.getBeneficiary());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(description, document.getDescription());
        assertEquals(dateOfInclusion, document.getDateOfInclusion());
        assertEquals(dateOfUpdate, document.getDateOfUpdate());
    }

    @Test
    void testDocumentId() {

        Document document = new Document();

        document.setId(1L);

        assertEquals(1L, document.getId());
    }
}
