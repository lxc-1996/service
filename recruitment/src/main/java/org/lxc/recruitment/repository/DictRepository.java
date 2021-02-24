package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.DictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictRepository extends JpaRepository<DictEntity, Long> {
	
}
