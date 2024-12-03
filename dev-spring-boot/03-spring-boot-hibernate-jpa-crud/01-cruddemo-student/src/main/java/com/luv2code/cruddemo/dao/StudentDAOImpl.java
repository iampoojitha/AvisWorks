package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // define constructor for entity manager for dependency injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Creating -- define a method to save the student object
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    // Reading -- the table information from Database
    public Student findById(Integer id){
        return  entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> queryStudent() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student",Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create a query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student where lastName= :theData",Student.class);

        // set query parameter
        theQuery.setParameter("theData",lastName);

        // returning the result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // find the student with id
        Student student = entityManager.find(Student.class,id);

        // removing student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int rowsReturned = entityManager.createQuery("Delete from Student").executeUpdate();
        return rowsReturned;
    }

}
