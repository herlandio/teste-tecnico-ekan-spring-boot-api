package br.com.herlandio7.ekantestspringbootapi.controllers;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.models.Document;
import br.com.herlandio7.ekantestspringbootapi.services.BeneficiaryService;
import br.com.herlandio7.ekantestspringbootapi.services.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BeneficiaryControllerTest {

    @InjectMocks
    private BeneficiaryController beneficiaryController;

    @Mock
    private BeneficiaryService beneficiaryService;

    @Mock
    private DocumentService documentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBeneficiary() {
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO(
            "John Doe",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        Beneficiary savedBeneficiary = new Beneficiary("John Doe",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now());
        when(beneficiaryService.saveBeneficiary(any(Beneficiary.class))).thenReturn(savedBeneficiary);

        ResponseEntity<Beneficiary> response = beneficiaryController.createBeneficiary(beneficiaryDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedBeneficiary, response.getBody());
        verify(beneficiaryService, times(1)).saveBeneficiary(any(Beneficiary.class));
    }

    @Test
    void testUpdateBeneficiary_Success() {
        Long beneficiaryId = 1L;
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO(
            "John Doe",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        Beneficiary existingBeneficiary = new Beneficiary("John Doe",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now());
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(existingBeneficiary);
        when(beneficiaryService.saveBeneficiary(any(Beneficiary.class))).thenReturn(existingBeneficiary);

        ResponseEntity<Beneficiary> response = beneficiaryController.updateBeneficiary(beneficiaryId, beneficiaryDTO);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(existingBeneficiary, response.getBody());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(beneficiaryService, times(1)).saveBeneficiary(any(Beneficiary.class));
    }

    @Test
    void testUpdateBeneficiary_NotFound() {
        Long beneficiaryId = 1L;
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO(
            "John Doe",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(null);

        ResponseEntity<Beneficiary> response = beneficiaryController.updateBeneficiary(beneficiaryId, beneficiaryDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(beneficiaryService, never()).saveBeneficiary(any(Beneficiary.class));
    }

    @Test
    void testListBeneficiary() {
        List<Beneficiary> beneficiaries = Arrays.asList(
            new Beneficiary(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now(),
                LocalDateTime.now()),
            new Beneficiary(
                "John Doe 2",
                "123456789",
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now(),
                LocalDateTime.now())
        );
        when(beneficiaryService.all()).thenReturn(beneficiaries);

        ResponseEntity<List<Beneficiary>> response = beneficiaryController.listBeneficiary();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(beneficiaries, response.getBody());
        verify(beneficiaryService, times(1)).all();
    }

    @Test
    void testRemoveBeneficiary() {
        Long beneficiaryId = 1L;
        HashMap<String, String> expectedMessage = new HashMap<>();
        expectedMessage.put("message", "Excluído");

        ResponseEntity<HashMap<String, String>> response = beneficiaryController.removeBeneficiary(beneficiaryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
        verify(beneficiaryService, times(1)).remove(beneficiaryId);
    }

    @Test
    void testCreateDocument_Success() {
        Long beneficiaryId = 1L;
        DocumentDTO documentDTO = new DocumentDTO(
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ID Card", 
            "National ID");
        Beneficiary beneficiary = new Beneficiary(
            "John Doe 2",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now());
        Document savedDocument = new Document(
            beneficiary, 
            "ID Card", 
            "National ID", 
            LocalDateTime.now(),
            LocalDateTime.now());
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(beneficiary);
        when(documentService.saveDocument(any(Document.class))).thenReturn(savedDocument);

        ResponseEntity<Document> response = beneficiaryController.createDocument(beneficiaryId, documentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDocument, response.getBody());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(documentService, times(1)).saveDocument(any(Document.class));
    }

    @Test
    void testCreateDocument_BeneficiaryNotFound() {
        Long beneficiaryId = 1L;
        DocumentDTO documentDTO = new DocumentDTO(
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ID Card", 
            "National ID");
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(null);

        ResponseEntity<Document> response = beneficiaryController.createDocument(beneficiaryId, documentDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(documentService, never()).saveDocument(any(Document.class));
    }

    @Test
    void testListDocuments_Success() {
        Long beneficiaryId = 1L;
        Beneficiary beneficiary = new Beneficiary(
            "John Doe 2",
            "123456789",
            LocalDate.of(1990, 1, 1),
            LocalDateTime.now(),
            LocalDateTime.now());
        Document document = new Document(
            beneficiary, 
            "ID Card", 
            "National ID", 
            LocalDateTime.now(),
            LocalDateTime.now());
        
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(beneficiary);
        when(documentService.getById(beneficiary)).thenReturn(Collections.singletonList(document));

        ResponseEntity<List<Document>> response = beneficiaryController.listDocuments(beneficiaryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(document), response.getBody());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(documentService, times(1)).getById(beneficiary);
    }

    @Test
    void testListDocuments_BeneficiaryNotFound() {
        
        Long beneficiaryId = 1L;
        when(beneficiaryService.findById(beneficiaryId)).thenReturn(null);

        ResponseEntity<List<Document>> response = beneficiaryController.listDocuments(beneficiaryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(beneficiaryService, times(1)).findById(beneficiaryId);
        verify(documentService, never()).getById(any(Beneficiary.class));
    }
}
