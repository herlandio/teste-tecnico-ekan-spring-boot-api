package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.models.Document;
import br.com.herlandio7.ekantestspringbootapi.services.BeneficiaryService;
import br.com.herlandio7.ekantestspringbootapi.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Beneficiary endpoint")
@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private DocumentService documentService;

    @Operation(summary = "Create a beneficiary")
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

    @Operation(summary = "Update by beneficiary id")
    @PutMapping("/update/beneficiary/{beneficiaryId}")
    public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable(name = "beneficiaryId") Long beneficiaryId,
            @RequestBody BeneficiaryDTO beneficiaryDTO) {
        var beneficiary = beneficiaryService.findById(beneficiaryId);
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }
        beneficiary.setName(beneficiaryDTO.getName());
        beneficiary.setDateOfBirth(beneficiaryDTO.getDateOfBirth());
        beneficiary.setPhoneNumber(beneficiaryDTO.getPhoneNumber());
        beneficiary.setDateOfInclusion(beneficiaryDTO.getDateOfInclusion());
        beneficiary.setDateOfUpdate(beneficiaryDTO.getDateOfUpdate());
        Beneficiary savedBeneficiary = beneficiaryService.saveBeneficiary(beneficiary);
        return new ResponseEntity<>(savedBeneficiary, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Create document by beneficiary id")
    @PostMapping("/create/document/{beneficiaryId}")
    public ResponseEntity<Document> createDocument(@PathVariable(name = "beneficiaryId") Long beneficiaryId,
            @RequestBody DocumentDTO documentDTO) {
        var beneficiary = beneficiaryService.findById(beneficiaryId);
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

    @Operation(summary = "List beneficiaries")
    @GetMapping("/list/beneficiaries")
    public ResponseEntity<List<Beneficiary>> listBeneficiary() {
        List<Beneficiary> allBeneficiaries = beneficiaryService.all();
        return ResponseEntity.ok(allBeneficiaries);
    }

    @Operation(summary = "List documents by beneficiary id")
    @GetMapping("/list/documents/{beneficiaryId}")
    public ResponseEntity<List<Document>> listDocuments(@PathVariable(name = "beneficiaryId") Long beneficiaryId) {
        var beneficiary = beneficiaryService.findById(beneficiaryId);
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }
        List<Document> document = documentService.getById(beneficiary);
        return ResponseEntity.ok(document);
    }

    @Operation(summary = "Delete beneficiary by beneficiary id")
    @DeleteMapping("/delete/beneficiary/{beneficiaryId}")
    public ResponseEntity<HashMap<String, String>> removeBeneficiary(
            @PathVariable(name = "beneficiaryId") Long beneficiaryDTO) {
        beneficiaryService.remove(beneficiaryDTO);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Excluído");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
