package br.com.herlandio7.ekantestspringbootapi.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeneficiaryTest {

    @Test
    void testBeneficiaryConstructorAndGetters() {
        
        String name = "John Doe";
        String phoneNumber = "123456789";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();
        
        Beneficiary beneficiary = new Beneficiary(name, phoneNumber, dateOfBirth, dateOfInclusion, dateOfUpdate);
        
        assertEquals(name, beneficiary.getName());
        assertEquals(phoneNumber, beneficiary.getPhoneNumber());
        assertEquals(dateOfBirth, beneficiary.getDateOfBirth());
        assertEquals(dateOfInclusion, beneficiary.getDateOfInclusion());
        assertEquals(dateOfUpdate, beneficiary.getDateOfUpdate());
    }

    @Test
    void testBeneficiarySetters() {
        
        Beneficiary beneficiary = new Beneficiary();
        
        String name = "Jane Doe";
        String phoneNumber = "987654321";
        LocalDate dateOfBirth = LocalDate.of(1995, 5, 5);
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();

        beneficiary.setName(name);
        beneficiary.setPhoneNumber(phoneNumber);
        beneficiary.setDateOfBirth(dateOfBirth);
        beneficiary.setDateOfInclusion(dateOfInclusion);
        beneficiary.setDateOfUpdate(dateOfUpdate);
        
        assertEquals(name, beneficiary.getName());
        assertEquals(phoneNumber, beneficiary.getPhoneNumber());
        assertEquals(dateOfBirth, beneficiary.getDateOfBirth());
        assertEquals(dateOfInclusion, beneficiary.getDateOfInclusion());
        assertEquals(dateOfUpdate, beneficiary.getDateOfUpdate());
    }

    @Test
    void testBeneficiaryDocuments() {

        Beneficiary beneficiary = new Beneficiary();
        List<Document> documents = new ArrayList<>();
        documents.add(new Document());

        beneficiary.setDocuments(documents);

        assertNotNull(beneficiary.getDocuments());
        assertEquals(1, beneficiary.getDocuments().size());
    }

    @Test
    void testBeneficiaryId() {

        Beneficiary beneficiary = new Beneficiary();

        beneficiary.setId(1L);

        assertEquals(1L, beneficiary.getId());
    }
}

