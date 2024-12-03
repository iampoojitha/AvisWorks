package com.luv2code.springboot.crud.demo.EmployeeDAO;

import com.luv2code.springboot.crud.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "ramesh")
public interface EmployeeJpaRepository extends JpaRepository<Employee,Integer> {
    // That's it no need to write any implementation class and code here.😂😂
}
