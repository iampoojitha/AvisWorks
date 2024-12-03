package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.utility.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO{

    // EntityManager Referance
    private EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book fetchBookByID(Integer id) {
        return entityManager.find(Book.class,id);
    }

}
