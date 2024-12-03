package com.luv2code.springboot.crud.demo.EmployeeDAO;

import com.luv2code.springboot.crud.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee,Integer> {
    // That's it doesn't need to write any implementation class and code here.😂😂
}
