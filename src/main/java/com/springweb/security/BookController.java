package com.springweb.security;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @RequestMapping(method = RequestMethod.POST)
  public Map<String, Object> createBook(@RequestBody Map<String, Object> bookMap){
    Book book = new Book(bookMap.get("name").toString(),
        bookMap.get("isbn").toString(),
        bookMap.get("author").toString(),
        Integer.parseInt(bookMap.get("pages").toString()));

    bookRepository.save(book);
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("message", "Book created successfully");
    response.put("book", book);
    return response;
  }

  @RequestMapping(method = RequestMethod.GET, value="/{bookId}")
  public Book getBookDetails(@PathVariable("bookId") String bookId){
    return bookRepository.findOne(bookId);
  }
  
  @RequestMapping(method = RequestMethod.PUT, value="/{bookId}")
  public Map<String, Object> editBook(@RequestBody Map<String, Object> editMap, @PathVariable("bookId") String bookId){
	  Book book = new Book(editMap.get("name").toString(),
			  editMap.get("isbn").toString(),
			  editMap.get("author").toString(),
			  Integer.parseInt(editMap.get("pages").toString()));
	  
	  book.setId(bookId);
	 
	  Map<String, Object> response = new LinkedHashMap<String, Object>();
	  response.put("message", "Book updated Sucessfully");
	  response.put("book", book);
	  return response;
  }
  
   @RequestMapping(method = RequestMethod.GET)
   public Map<String, Object> getAllBooks(){
	   List<Book> books = this.bookRepository.findAll();
	   Map<String, Object> response = new LinkedHashMap<String, Object>();
	   response.put("Book Size", books.size());
	   response.put("book",books);
       return response;
	   
   }
  
   @RequestMapping(method=RequestMethod.DELETE, value="/{bookId}")
   public Map<String, String> deleteBook(@PathVariable()String bookId){
	   Map<String,String> response = new HashMap<String,String>();
	   bookRepository.delete(bookId);
	   response.put("Message", "Delete sucessfully");
	   return response;
	   
   }
}

