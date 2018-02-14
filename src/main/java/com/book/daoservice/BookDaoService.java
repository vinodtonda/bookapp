package com.book.daoservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.book.models.Book;

public interface BookDaoService {

	@Autowired
	void addBook(Book book);

	List<Book> allBooksList();

	void deleteBook(int id);

	List<Book> editBook(int id);

}
