package com.luv2code.springboot.crud.demo.EmployeeDAO;

import com.luv2code.springboot.crud.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> getAll() {
        TypedQuery<Employee> query = entityManager.createQuery("From Employee",Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int employeeId) {
        return entityManager.find(Employee.class,employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteId(int employeeId) {
        Employee employee = entityManager.find(Employee.class,employeeId);
        entityManager.remove(employee);
    }
}
