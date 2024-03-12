package br.com.herlandio7.ekantestspringbootapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.services.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

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

    @GetMapping("/list/beneficiaries")
    public ResponseEntity<List<Beneficiary>> listBeneficiary() {
        List<Beneficiary> allBeneficiaries = beneficiaryService.all();
        return ResponseEntity.ok(allBeneficiaries);
    }

}
