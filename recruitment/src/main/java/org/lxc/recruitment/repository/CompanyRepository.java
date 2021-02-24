package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.CompanyEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
	
}
