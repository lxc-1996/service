package org.lxc.recruitment.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lxc.recruitment.entity.ResumeEntity;

import org.lxc.recruitment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
	
	@Nullable
	List<ResumeEntity> findByPersonalId(@NotNull Object id);
}
