package com.employee.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.model.Employee;
import com.employee.app.service.IEmployeeService;

@RestController
@RequestMapping("/employee/app/rest/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	private IEmployeeService empservice;

	@PostMapping("/saveemployee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		ResponseEntity<?> resp = null;
		try {
			boolean createEmployee = empservice.createEmployee(employee);
			if (createEmployee) {
				resp = new ResponseEntity<String>("Employee Created", HttpStatus.CREATED);
			} else {
				resp = new ResponseEntity<String>("EMPLOYEE NOT CREATED", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("SOME ERROR OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}
		return resp;
	}

	@GetMapping("/getallemployee")
	public ResponseEntity<?> getAllEmployee() {
		ResponseEntity<?> resp = null;

		try {
			List<Employee> allEmployee = empservice.getAllEmployee();
			if (allEmployee != null) {
				resp = new ResponseEntity<List<Employee>>(allEmployee, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>("NO RECORD FOUND", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("SOME ERROOR OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable("id") Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Employee oneEmpoyee = empservice.getOneEmpoyee(id);
			if (oneEmpoyee != null) {
				resp = new ResponseEntity<Employee>(oneEmpoyee, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>("NO RECORD FOUND", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>("SOME ERROR OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@DeleteMapping("/deleteemp/{eid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("eid") Integer eid) {

		ResponseEntity<?> resp = null;
		try {
			boolean deleteEmployee = empservice.deleteEmployee(eid);
			if (deleteEmployee) {
				resp = new ResponseEntity<String>("EMPLOYEE DELETED SUCCESFULLY", HttpStatus.OK);
			} else {

				resp = new ResponseEntity<String>("EMPLOYEE DELETED FAILED", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("SOME ERROR OCCUFRED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PutMapping("/updateemp")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {

		ResponseEntity<?> resp = null;
		try {
			boolean exist = empservice.isExist(employee.getEid());
			if (exist) {
				empservice.createEmployee(employee);
			} else {
				resp = new ResponseEntity<Employee>(new Employee(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<>("SOME ERROR OCCURED", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}
