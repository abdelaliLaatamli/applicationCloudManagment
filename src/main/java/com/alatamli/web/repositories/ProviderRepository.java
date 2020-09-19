package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alatamli.web.entities.ProviderEntity;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

}
