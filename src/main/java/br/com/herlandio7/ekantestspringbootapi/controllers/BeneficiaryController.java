package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.models.Document;
import br.com.herlandio7.ekantestspringbootapi.services.BeneficiaryService;
import br.com.herlandio7.ekantestspringbootapi.services.DocumentService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private DocumentService documentService;

    @PostMapping("/create/beneficiary")
    public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        Beneficiary newBeneficiary = new Beneficiary(
                beneficiaryDTO.getName(),
                beneficiaryDTO.getPhoneNumber(),
                beneficiaryDTO.getDateOfBirth(),
                beneficiaryDTO.getDateOfInclusion(),
                beneficiaryDTO.getDateOfUpdate());
        Beneficiary savedBeneficiary = beneficiaryService.saveBeneficiary(newBeneficiary);
        return new ResponseEntity<>(savedBeneficiary, HttpStatus.CREATED);
    }

    @PostMapping("/create/document")
    public ResponseEntity<Document> createDocument(@RequestBody DocumentDTO documentDTO) {
        var beneficiary = beneficiaryService.findById(documentDTO.getBeneficiaryId());
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }
        Document newDocument = new Document(
                beneficiary,
                documentDTO.getDocumentType(),
                documentDTO.getDescription(),
                documentDTO.getDateOfInclusion(),
                documentDTO.getDateOfUpdate());
        Document savedDocument = documentService.saveDocument(newDocument);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @GetMapping("/list/beneficiaries")
    public ResponseEntity<List<Beneficiary>> listBeneficiary() {
        List<Beneficiary> allBeneficiaries = beneficiaryService.all();
        return ResponseEntity.ok(allBeneficiaries);
    }

    @GetMapping("/list/documents/{beneficiaryId}")
    public ResponseEntity<List<Document>> listDocuments(@PathVariable(name = "beneficiaryId") Long beneficiaryId) {
        var beneficiary = beneficiaryService.findById(beneficiaryId);
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }
        List<Document> document = documentService.getById(beneficiary);
        return ResponseEntity.ok(document);
    }
}
