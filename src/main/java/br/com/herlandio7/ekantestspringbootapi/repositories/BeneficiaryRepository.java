package br.com.herlandio7.ekantestspringbootapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.herlandio7.ekantestspringbootapi.models.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByUsername(String username);
}
