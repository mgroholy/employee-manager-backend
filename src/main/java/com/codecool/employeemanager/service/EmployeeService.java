package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if(employeeOptional.isEmpty()){
            employeeRepository.save(employee);
            return employee;
        } else {
            throw new IllegalArgumentException("Email address already in use.");
        }
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}
