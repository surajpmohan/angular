package org.spm.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.spm.bookstore.beans.Book;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class BookDaoImpl implements BookDao {

	@Override
	public void insert(Book book) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("bookdb");
		db.getCollection("book").insertOne(Document.parse(book.toJson()));
		mongoClient.close();
	}

	@Override
	public void update(Book oldBook, Book newBook) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("bookdb");
		System.out.println(oldBook.toJson());
		System.out.println(newBook.toJson());
		db.getCollection("book").updateOne(Document.parse(oldBook.toJson()), new Document("$set", Document.parse(newBook.toJson())));
		mongoClient.close();
	}

	@Override
	public Book get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() {
		List<Book> list = new ArrayList<>();
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("bookdb");
		FindIterable<Document> docs = db.getCollection("book").find();
		for(Document doc: docs){
			Book book =Book.fromJson(doc.toJson());
			list.add(book);
		}
		mongoClient.close();
		return list;
	}

	@Override
	public void delete(Book book) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("bookdb");
		db.getCollection("book").deleteOne(Document.parse(book.toJson()));
		mongoClient.close();
	}

}
