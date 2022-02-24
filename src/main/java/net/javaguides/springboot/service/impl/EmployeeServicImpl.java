package net.javaguides.springboot.service.impl;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServicImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	//Constructor based Dependency Injection
	
	public EmployeeServicImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

}
