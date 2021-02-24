package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.CollectionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
	
	List<CollectionEntity> findByUserId(Long id);
}
