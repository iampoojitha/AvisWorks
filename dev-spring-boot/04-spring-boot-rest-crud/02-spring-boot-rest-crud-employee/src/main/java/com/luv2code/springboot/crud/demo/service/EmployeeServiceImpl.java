package com.luv2code.springboot.crud.demo.service;

import com.luv2code.springboot.crud.demo.EmployeeDAO.EmployeeDAO;
import com.luv2code.springboot.crud.demo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    @Override
    @Transactional
    public Employee findEmployeeById(int employeeId) {
        return employeeDAO.findById(employeeId);
    }

    @Transactional
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }


    @Transactional
    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeDAO.deleteId(employeeId);
    }
}
