package com.employee.app.service;

import java.util.List;

import com.employee.app.model.Employee;

public interface IEmployeeService {

	public boolean createEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee getOneEmpoyee(Integer id);

	public boolean deleteEmployee(Integer id);
	
	public boolean isExist(Integer id);

}
