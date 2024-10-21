package br.com.herlandio7.ekantestspringbootapi.services;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.models.Document;
import br.com.herlandio7.ekantestspringbootapi.repositories.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDocument() {
        Document document = new Document();

        when(documentRepository.save(any(Document.class))).thenReturn(document);

        Document savedDocument = documentService.saveDocument(document);

        assertNotNull(savedDocument);
        verify(documentRepository, times(1)).save(document);
    }

    @Test
    void testGetById() {
        Beneficiary beneficiary = new Beneficiary();
        Document document1 = new Document();
        Document document2 = new Document();

        when(documentRepository.findByBeneficiary(beneficiary)).thenReturn(Arrays.asList(document1, document2));

        List<Document> documents = documentService.getById(beneficiary);

        assertEquals(2, documents.size());
        verify(documentRepository, times(1)).findByBeneficiary(beneficiary);
    }

    @Test
    void testGetById_NoDocuments() {
        Beneficiary beneficiary = new Beneficiary();

        when(documentRepository.findByBeneficiary(beneficiary)).thenReturn(Arrays.asList());

        List<Document> documents = documentService.getById(beneficiary);

        assertTrue(documents.isEmpty());
        verify(documentRepository, times(1)).findByBeneficiary(beneficiary);
    }
}
