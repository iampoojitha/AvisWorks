package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> queryStudent();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(int id);

    int deleteAll();
}
