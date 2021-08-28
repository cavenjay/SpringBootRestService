package com.cavenproject;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.cavenproject.controller.Library;

@SpringBootTest
public class testsIT {
	
	// mvntest
	
	//TestResttemplate
	//Rest Assured
	@Test
	public void getAuthorNameBooksTest() throws JSONException
	{
		String expected= "[\r\n"
				+ "    {\r\n"
				+ "        \"book_name\": \"Cyber\",\r\n"
				+ "        \"id\": \"12\",\r\n"
				+ "        \"isbn\": \"fdsefr3\",\r\n"
				+ "        \"aisle\": 43,\r\n"
				+ "        \"author\": \"Banana\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"book_name\": \"Bay\",\r\n"
				+ "        \"id\": \"123\",\r\n"
				+ "        \"isbn\": \"fdsefr3\",\r\n"
				+ "        \"aisle\": 43,\r\n"
				+ "        \"author\": \"Banana\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"book_name\": \"Dog\",\r\n"
				+ "        \"id\": \"1234\",\r\n"
				+ "        \"isbn\": \"fdsefr3\",\r\n"
				+ "        \"aisle\": 43,\r\n"
				+ "        \"author\": \"Banana\"\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		ResponseEntity<String> response =restTemplate.getForEntity("http://localhost:8080/getBooks/author?authorname=Banana", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	@Test
	public void addBookIntegrationTest()
	{
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Library> request = new HttpEntity<Library> (buildLibrary(), headers);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addBook", request, String.class);
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		assertEquals(buildLibrary().getId(),response.getHeaders().get("unique").get(0));
	}
	
	public Library buildLibrary()
	{
		Library lib = new Library();
		lib.setAisle(322);
		lib.setBook_name("SpringTest");
		lib.setIsbn("sfe");
		lib.setAuthor("Jay");
		lib.setId("sfe322");
		return lib;
	}

}
