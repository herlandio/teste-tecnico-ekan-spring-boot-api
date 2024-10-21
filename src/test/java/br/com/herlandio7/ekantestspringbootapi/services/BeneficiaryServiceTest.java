package br.com.herlandio7.ekantestspringbootapi.services;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.repositories.BeneficiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BeneficiaryServiceTest {

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();

        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);

        Beneficiary savedBeneficiary = beneficiaryService.saveBeneficiary(beneficiary);

        assertNotNull(savedBeneficiary);
        verify(beneficiaryRepository, times(1)).save(beneficiary);
    }

    @Test
    void testAll() {
        Beneficiary beneficiary1 = new Beneficiary();
        Beneficiary beneficiary2 = new Beneficiary();
        when(beneficiaryRepository.findAll()).thenReturn(Arrays.asList(beneficiary1, beneficiary2));

        List<Beneficiary> beneficiaries = beneficiaryService.all();

        assertEquals(2, beneficiaries.size());
        verify(beneficiaryRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Beneficiary beneficiary = new Beneficiary();
        when(beneficiaryRepository.findById(id)).thenReturn(Optional.of(beneficiary));

        Beneficiary foundBeneficiary = beneficiaryService.findById(id);

        assertNotNull(foundBeneficiary);
        verify(beneficiaryRepository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(beneficiaryRepository.findById(id)).thenReturn(Optional.empty());

        Beneficiary foundBeneficiary = beneficiaryService.findById(id);

        assertNull(foundBeneficiary);
        verify(beneficiaryRepository, times(1)).findById(id);
    }

    @Test
    void testRemove() {
        Long id = 1L;

        beneficiaryService.remove(id);

        verify(beneficiaryRepository, times(1)).deleteById(id);
    }
}
