package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void save(Employee employee);
    void update(Employee employee);
    void deleteById(int id);
    List<Employee> findAll();
    Employee findById(int id);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByName(String name);
    List<Employee> findByDepartment(String departmentName);

}
