package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.utility.Book;

public interface BookDAO {
    void save(Book book);
    Book fetchBookByID(Integer id);
}
