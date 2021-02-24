package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.JobEntity;

import org.lxc.recruitment.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
	
	List<JobEntity> findByCompanyId(Long id);
}
