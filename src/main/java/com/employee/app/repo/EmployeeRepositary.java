package com.employee.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.app.model.Employee;

public interface EmployeeRepositary extends JpaRepository<Employee, Integer>{

}
