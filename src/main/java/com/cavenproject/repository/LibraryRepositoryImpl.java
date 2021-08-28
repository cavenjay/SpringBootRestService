package com.cavenproject.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cavenproject.controller.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom
{
	@Autowired
	LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String authorName)
	{
		//To store author name in list
		List<Library>bookswithAuthor = new ArrayList<Library>();
		
		//To find all the books
		List<Library>books =repository.findAll();
		for(Library item : books) 
		{
			if(item.getAuthor().equalsIgnoreCase(authorName)) 
			{
				bookswithAuthor.add(item);
			}
		}
		
		return bookswithAuthor;
	}
}
