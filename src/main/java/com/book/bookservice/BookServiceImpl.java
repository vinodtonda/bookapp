package com.book.bookservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.book.daoservice.BookDaoService;
import com.book.models.Book;
@Service("BookService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDaoService bookdaoservice;

	public void addBook(Book book) {
		// TODO Auto-generated meth
		bookdaoservice.addBook(book);
		
	}

	
	public List<Book> allBooksList() {
		// TODO Auto-generated method stub
		return bookdaoservice.allBooksList();
	}


	public void deleteBook(int id) {
		bookdaoservice.deleteBook(id);
		
	}


	public List<Book> editBook(int id) {
		// TODO Auto-generated method stub
		return bookdaoservice.editBook(id);
	}

}
