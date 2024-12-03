package com.luv2code.springboot.crud.demo.EmployeeDAO;

import com.luv2code.springboot.crud.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO{
   List<Employee> getAll();

   Employee findById(int employeeId);

   Employee save(Employee employee);

   void deleteId(int employeeId);

}
