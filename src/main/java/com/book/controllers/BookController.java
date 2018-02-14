package com.book.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.book.bookservice.BookService;
import com.book.models.Book;

@RestController
@EnableWebMvc

		/**
		 * 
		 * @author vin
		 *
		 */
// Book controller to handle requests
public class BookController {
	
	
	@Autowired
	private BookService bookservice;

	private static final Logger logger = Logger.getLogger(BookController.class);

	//Dafault mapping for index page to load
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("command") Book book) {
		
	
		return new ModelAndView("index");
	}
	
	// Method to save book to DataStore
	@RequestMapping(value="/savebook",method = RequestMethod.POST)
	public ModelAndView savebook(@ModelAttribute("command") Book book){
		bookservice.addBook(book);
		return new ModelAndView("index");
		
	}
	
	//Method to edit book
	//here parameter will come from ajax call
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
	@ResponseBody
	public List<Book> editBook(@PathVariable("id") int id,@ModelAttribute("command") Book book) {
		
		
		return bookservice.editBook(id);
		
	}
	
	//Method to delete book based on id
	//here parameter will come from ajax call
	@RequestMapping(value="/remove/{id}",method = RequestMethod.GET)
	public ModelAndView deleteBook(@PathVariable("id") int id,@ModelAttribute("command") Book book) {
		System.out.println(id);
		
		
		bookservice.deleteBook(id);// calling book service to delete book based on id
		return new ModelAndView("index");
		
	}
	
	@RequestMapping(value="/allbooks",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> allBooks() {
		
		List<Book> booklist=bookservice.allBooksList();
		  
		  return booklist;
		
	}
}
