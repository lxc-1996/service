package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.LetterEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
	
	List<LetterEntity> findByReceiver(Long id);
}
