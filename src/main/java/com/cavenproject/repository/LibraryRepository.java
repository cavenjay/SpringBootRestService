package com.cavenproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavenproject.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
	
	
	
}
