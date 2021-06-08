package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.*;
import com.codecool.employeemanager.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;
    private PositionService positionService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService, PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.positionService = positionService;
    }

    public Employee addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if(employeeOptional.isEmpty()){
            Department department = departmentService.findByName(employee.getDepartment().getName());
            Position position = positionService.findByName(employee.getPosition().getName());
            employee.setPosition(position);
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

    public void updateEmployeeDetail(int id, Map<String, String> data) {
        Employee foundEmployee = findEmployeeById(id);
        boolean hasUpdate = false;

        for (String fieldName : data.keySet()) {
            hasUpdate = true;
            String fieldValue = data.get(fieldName);

            switch (fieldName) {
                case "Name": foundEmployee.setName(fieldValue); break;
                case "Email": foundEmployee.setEmail(fieldValue); break;
                case "Phone number": foundEmployee.setPhoneNumber(fieldValue); break;
                case "Date of birth": foundEmployee.setDateOfBirth(LocalDate.parse(fieldValue)); break;
                case "Department": foundEmployee.setDepartment(departmentService.findByName(fieldValue)); break;
                case "Position": foundEmployee.setPosition(positionService.findByName(fieldValue)); break;
                case "Clearance level": foundEmployee.setClearanceLevel(ClearanceLevel.valueOf(fieldValue)); break;
                case "Status": foundEmployee.setStatus(Status.valueOf(fieldValue)); break;
                case "Date of hire": foundEmployee.setDateOfHire(LocalDate.parse(fieldValue)); break;
                case "Date of termination":
                    if (fieldValue == null) foundEmployee.setDateOfTermination(null);
                    else foundEmployee.setDateOfTermination(LocalDate.parse(fieldValue));
                    break;
                default:
                    log.error("Update error : case " + fieldName + " not found in switch.");
                    throw new IllegalArgumentException("An error occurred on our side.");
            }
        }

        if (hasUpdate) employeeRepository.save(foundEmployee);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartmentName(department);
    }

    public List<Employee> findAllByName(String name){
        return employeeRepository.findByNameContainingIgnoreCase(name);
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
