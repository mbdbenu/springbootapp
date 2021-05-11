package com.employee.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.model.Employee;
import com.employee.app.repo.EmployeeRepositary;
import com.employee.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepositary emplrepo;

	@Override
	public boolean createEmployee(Employee employee) {

		return emplrepo.save(employee).getEid() != null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return emplrepo.findAll();
	}

	@Override
	public Employee getOneEmpoyee(Integer id) {
		Optional<Employee> findById = emplrepo.findById(id);
		if (findById.isPresent()) {
			Employee empId = findById.get();
			return empId;
		}
		return null;
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		emplrepo.deleteById(id);
		return true;
	}
	@Override
	public boolean isExist(Integer id) {
		return emplrepo.existsById(id);
	}

}
