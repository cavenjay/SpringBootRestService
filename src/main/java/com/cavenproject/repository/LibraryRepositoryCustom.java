package com.cavenproject.repository;

import java.util.List;

import com.cavenproject.controller.Library;

public interface LibraryRepositoryCustom {
	
	List<Library> findAllByAuthor(String authorName);
}
