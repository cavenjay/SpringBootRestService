package com.cavenproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cavenproject.controller.Library;
import com.cavenproject.repository.LibraryRepository;

@SpringBootApplication
public class SpringBootRestServiceApplication {

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}

	/*
	 * //@Override public void run(String[] args) { //Find by ID function Library
	 * lib = repository.findById("123lkhs").get();
	 * System.out.println(lib.getAuthor());
	 * 
	 * //Input new data function Library en = new Library(); en.setAisle(456);
	 * en.setAuthor("Jay"); en.setBook_name("BakingBook"); en.setIsbn("cjt");
	 * en.setId("456cjt"); //repository.save(en);
	 * 
	 * //Listing all library function List<Library> allrecords =
	 * repository.findAll();
	 * 
	 * for(Library item: allrecords) { System.out.println(item.getBook_name());
	 * 
	 * }
	 * 
	 * //Delete data Function //repository.delete(en);
	 * 
	 * 
	 * 
	 * }
	 */

}
