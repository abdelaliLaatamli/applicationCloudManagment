package com.alatamli.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alatamli.web.entities.InstanceEntity;

public interface InstanceRepository extends JpaRepository<InstanceEntity, Long> {

	InstanceEntity findByInstanceId(String string);

}
