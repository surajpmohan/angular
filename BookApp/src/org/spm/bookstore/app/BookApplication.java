package org.spm.bookstore.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.spm.bookstore.service.BookService;
@javax.ws.rs.ApplicationPath("rest")
public class BookApplication extends Application{
	 public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(BookService.class);
	        return classes;
	 }
}
