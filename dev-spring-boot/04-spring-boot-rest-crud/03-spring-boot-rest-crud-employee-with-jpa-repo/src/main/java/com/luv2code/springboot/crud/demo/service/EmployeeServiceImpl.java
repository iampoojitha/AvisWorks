package com.luv2code.springboot.crud.demo.service;

import com.luv2code.springboot.crud.demo.EmployeeDAO.EmployeeJpaRepository;
import com.luv2code.springboot.crud.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository employeeJpaRepository){
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        Optional<Employee> employee = employeeJpaRepository.findById(employeeId);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new RuntimeException("Provided Student ID not Found!");
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeJpaRepository.save(employee);
    }


    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeJpaRepository.deleteById(employeeId);
    }
}
