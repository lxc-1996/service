package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.AuditEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
	
}
