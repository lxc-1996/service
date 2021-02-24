package org.lxc.recruitment.repository;

import org.lxc.recruitment.entity.NewsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
}
