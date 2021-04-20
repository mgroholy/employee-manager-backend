package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.model.Status;
import com.codecool.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public Employee addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if(employeeOptional.isEmpty()){
            Department department = departmentService.findByName(employee.getDepartment().getName());
            employee.setDepartment(department);
            employee.setStatus(Status.ACTIVE);
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
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found by id - " + id));
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
        Department department = departmentService.findByName(employee.getDepartment().getName());
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartmentName(department);
    }

    public List<Employee> findAllByName(String name){
        return employeeRepository.findByNameContaining(name);
    }

    public Employee findEmployeeByEmail(String email){
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if(employee.isPresent()){
            return employee.get();
        } else {
            throw new NoSuchElementException("Employee not found by email - " + email);
        }
    }
}
