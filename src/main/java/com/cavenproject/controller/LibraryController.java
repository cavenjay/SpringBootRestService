package com.cavenproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cavenproject.repository.LibraryRepository;
import com.cavenproject.service.LibraryService;


@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library)
	{
		String id = libraryService.buildId(library.getIsbn(),library.getAisle()); //mock
		AddResponse ad = new AddResponse();
		
		if(!libraryService.checkBookAlreadyExist(id)) //mock
		{
			logger.info("Book do not exist so creating one");
		//add book details into database
		//jpa repository -save
		
		library .setId(id);
		repository.save(library); //mock
		HttpHeaders headers = new HttpHeaders();
		headers.add("unique", id);
		
		
		//response msg back to user
		ad.setMsg("Success Book is Added");
		ad.setId(library.getIsbn()+library.getAisle());
		//convert top json automatically by spring boot
		return new ResponseEntity<AddResponse>(ad,headers,HttpStatus.CREATED);
		}
		else
		{
			logger.info("Book exist so skipping creation");
			ad.setMsg("Book already exists!!!");
			ad.setId(id);
			// write the code to tell book already exists
			return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED);
		}
		
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value="id") String id)
	{
		try {
			Library lib =repository.findById(id).get();
			return lib;
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
	{
		
		return repository.findAllByAuthor(authorname);
		
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id") String id, @RequestBody Library library)
	{
		//Library existingBook = repository.findById(id).get();
		Library existingBook = libraryService.getBookById(id); //mock
		
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		
		repository.save(existingBook);
		
		return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteBook")
	
	public ResponseEntity<String> deleteBookById(@RequestBody Library library)
	{
		try {
		//Library libdelete =repository.findById(library.getId()).get();
		Library libdelete = libraryService.getBookById(library.getId());//mock
		repository.delete(libdelete);
		logger.info("Book is deleted!");
		return new ResponseEntity<>("Book is deleted!",HttpStatus.CREATED);
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/allBooks")
	public List<Library> getAllBooks()
	{
		List<Library> libAll =repository.findAll();
		return libAll;
	}
	
	
	
	
	
	
}
