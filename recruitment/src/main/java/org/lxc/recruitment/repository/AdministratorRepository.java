package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.AdministratorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Long> {
	
}
