package com.kegmil.mvista.repositories;

import com.kegmil.mvista.entities.ClientCredential;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientCredentialRepository extends BaseJpaRepository<ClientCredential, UUID>,
    ListPagingAndSortingRepository<ClientCredential, UUID> {
  Optional<ClientCredential> findByApiKey(String apiKey);
}
