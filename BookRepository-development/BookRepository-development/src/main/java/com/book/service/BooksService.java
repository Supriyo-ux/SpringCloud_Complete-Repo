package com.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.exception.BookNotFoundException;
import com.book.model.Book;
import com.book.repository.BooksRepository;

@Service
public class BooksService {

	@Autowired
	BooksRepository booksRepository;

	Book book = null;

	public Iterable<Book> getAllBooks() {
		// List<Book> books = new ArrayList<Book>();
		// booksRepository.findAll().forEach(books1 -> books.add(books1));
		return booksRepository.findAll();
	}

	public Book getBooksById(int id) {

		try {
			book = booksRepository.findById(id).get();
		} catch (Exception e) {
			throw new BookNotFoundException("Book Not Found : " + id);
		}
		return book; 
	}

	public Book getBooksByBookName(String bookName) {

		book = booksRepository.findbookByBookName(bookName);
		if (book == null) {
			throw new BookNotFoundException("Book Not Found with this bookname " + bookName);
		}
		return book;
	}

	public Iterable<Book> getBooksByAuthorName(String authorName) {
		
		Iterable<Book> bookByAuthor = booksRepository.findbookByAuthor(authorName);
		if (bookByAuthor == null) {
			throw new BookNotFoundException("Book Not Found with this authorName " + authorName);
		}
		return bookByAuthor;
	}

	public void saveOrUpdate(Book books) {
		booksRepository.save(books);
	}

	public void delete(int id) {
		try {
			book = booksRepository.findById(id).get();
			if (book == null) {
				throw new BookNotFoundException("Book Not Found : " + id);
			} else {
				booksRepository.deleteById(id);
			}

		} catch (Exception e) {
			throw new BookNotFoundException("Book Not Found : " + id);
		}
	}

	public void update(Book books, int bookid) {

		Book bookFromDB = booksRepository.findById(bookid).get();

		bookFromDB.setAuthor(books.getAuthor());
		bookFromDB.setBookid(bookFromDB.getBookid());
		bookFromDB.setBookname(books.getBookname());
		bookFromDB.setPrice(books.getPrice());

		booksRepository.save(bookFromDB);

	}
}