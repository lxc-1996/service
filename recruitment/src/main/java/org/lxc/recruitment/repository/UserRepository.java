package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	List<UserEntity> findAll();
	
	List<UserEntity> findByAccount(String id);
	
}
