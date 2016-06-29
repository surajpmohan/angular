package org.spm.bookstore.beans;

public class UpdateBookModel {
	private Book oldBook;
	private Book newBook;
	public Book getOldBook() {
		return oldBook;
	}
	public void setOldBook(Book oldBook) {
		this.oldBook = oldBook;
	}
	public Book getNewBook() {
		return newBook;
	}
	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}
}
