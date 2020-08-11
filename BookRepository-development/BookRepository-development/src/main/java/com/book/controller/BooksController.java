package com.book.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.model.Book;
import com.book.service.BooksService;


@RestController
@RequestMapping(value = "/book")
public class BooksController {
	
	@Autowired
	BooksService booksService;

	
	@GetMapping("/getAllBooks")
	private Iterable<Book> getAllBooks() {
		return booksService.getAllBooks();
	}

	@GetMapping("/bookDetails/{bookid}")
	private Book getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}
	
	@GetMapping("/bookDetailsbyName/{bookname}")
	private Book getBooksbyName(@PathVariable("bookname") String bookName) {
		return booksService.getBooksByBookName(bookName);
	}
   
	@GetMapping("/bookDetailsbyAuthor/{authorName}")
	private Iterable<Book> getBooksbyAuthorName(@PathVariable("authorName") String author) {
		return booksService.getBooksByAuthorName(author);
		
	}
	
	@DeleteMapping("/deleteBook/{bookid}")
	private ResponseEntity<?>  deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
		return ResponseEntity.ok("Book deleted successfully");
	}

	
	@PostMapping("/createBook")
	private ResponseEntity<?> saveBook(@RequestBody Book books) {
		booksService.saveOrUpdate(books);
		return ResponseEntity.ok("Book saved");
	}

	
	@PutMapping("/updateBook/{bookid}")
	private ResponseEntity<String> update(@RequestBody Book books,@PathVariable("bookid") int bookid) {
		//booksService.saveOrUpdate(books);
		booksService.update(books, bookid);
		return ResponseEntity.ok("Update Successfully");
	}
	
	
}
