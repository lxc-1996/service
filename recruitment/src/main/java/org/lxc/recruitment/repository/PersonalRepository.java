package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.PersonalEntity;

import org.lxc.recruitment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalRepository extends JpaRepository<PersonalEntity, Long> {
	
}
