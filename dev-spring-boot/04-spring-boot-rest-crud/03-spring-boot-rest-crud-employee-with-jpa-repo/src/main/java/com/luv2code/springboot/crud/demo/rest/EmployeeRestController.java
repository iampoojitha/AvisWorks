package com.luv2code.springboot.crud.demo.rest;

import com.luv2code.springboot.crud.demo.entity.Employee;
import com.luv2code.springboot.crud.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
//  Below method of doing is directly to the Employee and EmployeeDAO
//  private EmployeeDAO employeeDAO;
//  Instead link to ServiceDAO

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService= employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> allEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee fetchEmployeeById(@PathVariable int employeeId){
        Employee employee = employeeService.findEmployeeById(employeeId);
        if(employee == null){
            throw new RuntimeException("Employee Not Found!  "+employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee){
        return employeeService.saveEmployee(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee newEmployee){
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
