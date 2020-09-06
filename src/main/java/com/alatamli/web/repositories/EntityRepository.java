package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alatamli.web.entities.EntityEntity;

public interface EntityRepository extends JpaRepository<EntityEntity, Long> {

}
