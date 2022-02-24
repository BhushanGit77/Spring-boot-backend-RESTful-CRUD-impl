package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
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

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		
		if(!existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		
		existingEmployee.get().setFirstName(employee.getFirstName());
		existingEmployee.get().setLastName(employee.getLastName());
		existingEmployee.get().setEmail(employee.getEmail());
		
		return employeeRepository.save(existingEmployee.get());
		
	}

	@Override
	public void deleteEmployeeById(Long id) {
		
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		
		if(!existingEmployee.isPresent()) {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		employeeRepository.deleteById(id);
	}
}
