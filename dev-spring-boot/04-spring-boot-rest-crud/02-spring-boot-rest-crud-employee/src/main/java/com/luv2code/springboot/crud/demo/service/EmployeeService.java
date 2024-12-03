package com.luv2code.springboot.crud.demo.service;

import com.luv2code.springboot.crud.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee findEmployeeById(int employeeId);

    Employee saveEmployee(Employee employee);

    void deleteEmployeeById(int employeeId);

}
