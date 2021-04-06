package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpRepImplementation implements EmployeeRepository{

    private List<Employee> employees = new ArrayList<>();

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        return null;
    }

    @Override
    public List<Employee> findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> findByDepartment(String departmentName) {
        return null;
    }
}
