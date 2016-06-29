package org.spm.bookstore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.spm.bookstore.beans.Book;
import org.spm.bookstore.beans.UpdateBookModel;
import org.spm.bookstore.dao.BookDao;
import org.spm.bookstore.dao.BookDaoImpl;
import org.spm.bookstore.util.JsonUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.json.java.JSON;
import com.ibm.json.java.JSONObject;

@Path("/book")
public class BookService {
	
	@GET
	@Produces("application/json")
	public String getAllBooks(){
		BookDao dao = new BookDaoImpl();
		List<Book> books = dao.getAll();
		return JsonUtil.toJson(books);
	}
	
	@POST
	@Produces("application/json")
	public String insert(String input){
		System.out.println(input);
		Book book = Book.fromJson(input);
		BookDao dao = new BookDaoImpl();
		dao.insert(book);
		System.out.println(book.toString());
		return book.toJson();
	}
	
	@PUT
	@Produces("application/json")
	public String update(String input){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Book> map = mapper.readValue(input, new TypeReference<HashMap<String,Book>>(){});
			Book oldBook = map.get("oldBook");
			Book newBook = map.get("newBook");
			BookDao dao = new BookDaoImpl();
			dao.update(oldBook, newBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	@DELETE
	@Produces("application/json")
	public String delete(String input){
		System.out.println(input);
		Book book = Book.fromJson(input);
		BookDao dao = new BookDaoImpl();
		dao.delete(book);
		return book.toJson();
	}
}
