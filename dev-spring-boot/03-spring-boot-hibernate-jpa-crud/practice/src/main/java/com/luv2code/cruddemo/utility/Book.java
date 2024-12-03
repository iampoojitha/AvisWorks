package com.luv2code.cruddemo.utility;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "price")
    private int price;


    // define constructors

    public Book(){}

    public Book(String bookName, int price) {
        this.bookName = bookName;
        this.price = price;
    }

    // define getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // define toString method

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }
}
