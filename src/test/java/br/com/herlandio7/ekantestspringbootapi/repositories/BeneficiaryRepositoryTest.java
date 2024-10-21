package br.com.herlandio7.ekantestspringbootapi.repositories;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeneficiaryRepositoryTest {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @BeforeEach
    public void setUp() {
        beneficiaryRepository.deleteAll();
    }

    @Test
    @Rollback(false)
    void testSaveBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("John Doe");

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        assertThat(savedBeneficiary).isNotNull();
        assertThat(savedBeneficiary.getId()).isPositive();
        assertThat(savedBeneficiary.getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindById() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("Jane Doe");
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        Beneficiary foundBeneficiary = beneficiaryRepository.findById(savedBeneficiary.getId()).orElse(null);

        assertThat(foundBeneficiary).isNotNull();
        assertThat(foundBeneficiary.getId()).isEqualTo(savedBeneficiary.getId());
        assertThat(foundBeneficiary.getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testDeleteBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("Alice");
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        beneficiaryRepository.deleteById(savedBeneficiary.getId());

        Beneficiary foundBeneficiary = beneficiaryRepository.findById(savedBeneficiary.getId()).orElse(null);
        assertThat(foundBeneficiary).isNull();
    }
}
