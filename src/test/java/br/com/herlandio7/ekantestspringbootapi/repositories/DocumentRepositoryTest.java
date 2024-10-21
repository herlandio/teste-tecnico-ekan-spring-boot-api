package br.com.herlandio7.ekantestspringbootapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;
import br.com.herlandio7.ekantestspringbootapi.models.Document;

import java.util.List;

@DataJpaTest
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    private Beneficiary beneficiary;

    @BeforeEach
    public void setUp() {
        beneficiary = new Beneficiary();
        beneficiary.setName("John Doe");
        beneficiary = beneficiaryRepository.save(beneficiary);
    }

    @Test
    void testFindByBeneficiary() {
        Document document = new Document();
        document.setBeneficiary(beneficiary);
        document.setDescription("Document Description");
        documentRepository.save(document);

        List<Document> documents = documentRepository.findByBeneficiary(beneficiary);

        assertThat(documents).isNotEmpty();
    }
}
