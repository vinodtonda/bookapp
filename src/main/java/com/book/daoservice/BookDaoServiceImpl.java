package com.book.daoservice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.models.Book;

@Repository("BookDaoService")
public class BookDaoServiceImpl implements BookDaoService {

	@Autowired
	public SessionFactory sessionfactory;
	
	public void addBook(Book book) {
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		
		try {
			session.saveOrUpdate(book);
			tx.commit();
		}
		catch(HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
			    tx.rollback();
			}
		}
		
	}

	public List<Book> allBooksList() {
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Book> bookslist=null;
		
		try {
			bookslist=session.createCriteria(Book.class).list();
			
			tx.commit();
		}
		catch(HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
			    tx.rollback();
			}
		}
		return bookslist;
	}

	public void deleteBook(int id) {
		System.out.println(id);
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		Book book=new Book();
		book.setId(id);
		
		try {
			session.delete(book);
			
			tx.commit();
		}
		catch(HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
			    tx.rollback();
			}
		}
		
	}

	public List<Book> editBook(int id) {
		Session session=sessionfactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Book> bookedit=null;
		try {
			Criteria criteria=session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("id", id));
			bookedit=criteria.list();
			System.out.println("Book eidt object"+bookedit);
			tx.commit();
		}
		catch(HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
			    tx.rollback();
			}
		}
		return bookedit;
	}
	
}

