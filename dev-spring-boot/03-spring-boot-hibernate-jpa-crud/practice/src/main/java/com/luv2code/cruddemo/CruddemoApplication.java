package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.BookDAO;
import com.luv2code.cruddemo.utility.Book;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(BookDAO bookDAO){
		return (args)->{
			createBook(bookDAO);
			getBookByID(bookDAO);
		};
	}

	public void createBook(BookDAO bookDAO){
		Book tempBook = new Book("SPRING",1000);
		bookDAO.save(tempBook);
	}

	public void getBookByID(BookDAO bookDAO){
		System.out.println("Storing a Book...");
		Book tempBook = new Book("GREET",123);
		bookDAO.save(tempBook);
		System.out.println("Stored Book with ID "+tempBook.getId());
		Book book = bookDAO.fetchBookByID(tempBook.getId());
		System.out.println("Retrived Book: "+book);
	}
}
