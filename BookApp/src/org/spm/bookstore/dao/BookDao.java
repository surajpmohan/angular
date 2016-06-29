package org.spm.bookstore.dao;

import java.util.List;

import org.spm.bookstore.beans.Book;

public interface BookDao {
	public void insert(Book book);
	public void update(Book oldBook, Book newBook);
	public Book get(String id);
	public List<Book> getAll();
	public void delete(Book book);
}
