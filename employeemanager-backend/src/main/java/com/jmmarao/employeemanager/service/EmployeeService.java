package com.jmmarao.employeemanager.service;

import com.jmmarao.employeemanager.model.Employee;
import com.jmmarao.employeemanager.repository.EmployeeRepository;
import com.jmmarao.employeemanager.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        Employee employeeToUpdate = employeeRepository.getReferenceById(employee.getId());
        updateEmployeeData(employeeToUpdate, employee);
        return employeeRepository.save(employeeToUpdate);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found"));
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    private void updateEmployeeData(Employee employeeToUpdate, Employee employee) {
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setJobTitle(employee.getJobTitle());
        employeeToUpdate.setPhone(employee.getPhone());
        employeeToUpdate.setImageURL(employee.getImageURL());
    }
}
