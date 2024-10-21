package br.com.herlandio7.ekantestspringbootapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class BeneficiaryDTOTest {

    @Test
    void testBeneficiaryDTO_WithParameters() {
        String name = "John Doe";
        String phoneNumber = "1234567890";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();

        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO(name, phoneNumber, dateOfBirth, dateOfInclusion, dateOfUpdate);

        assertEquals(name, beneficiaryDTO.getName());
        assertEquals(phoneNumber, beneficiaryDTO.getPhoneNumber());
        assertEquals(dateOfBirth, beneficiaryDTO.getDateOfBirth());
        assertEquals(dateOfInclusion, beneficiaryDTO.getDateOfInclusion());
        assertEquals(dateOfUpdate, beneficiaryDTO.getDateOfUpdate());
    }

    @Test
    void testBeneficiaryDTO_WithoutParameters() {
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();

        String name = "Jane Doe";
        String phoneNumber = "0987654321";
        LocalDate dateOfBirth = LocalDate.of(1985, 5, 15);
        LocalDateTime dateOfInclusion = LocalDateTime.now();
        LocalDateTime dateOfUpdate = LocalDateTime.now();

        beneficiaryDTO.setName(name);
        beneficiaryDTO.setPhoneNumber(phoneNumber);
        beneficiaryDTO.setDateOfBirth(dateOfBirth);
        beneficiaryDTO.setDateOfInclusion(dateOfInclusion);
        beneficiaryDTO.setDateOfUpdate(dateOfUpdate);

        assertEquals(name, beneficiaryDTO.getName());
        assertEquals(phoneNumber, beneficiaryDTO.getPhoneNumber());
        assertEquals(dateOfBirth, beneficiaryDTO.getDateOfBirth());
        assertEquals(dateOfInclusion, beneficiaryDTO.getDateOfInclusion());
        assertEquals(dateOfUpdate, beneficiaryDTO.getDateOfUpdate());
    }
}

