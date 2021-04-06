package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmpRepImplementation implements EmployeeRepository{

    private List<Employee> employees = new ArrayList<>();

    public EmpRepImplementation() {
        // for testing purposes
        save(new Employee("John Doe", "john@doe.com", "IT", "36301234567", LocalDate.parse("1970-02-03"), ClearanceLevel.USER, "Developer"));
        save(new Employee("Jane Doe", "jane@doe.com", "HR", "36305671234", LocalDate.parse("1985-10-10"), ClearanceLevel.ADMIN, "Recruiter"));
        save(new Employee("Jack Smith", "jack@smith.com", "Sales", "36309876543", LocalDate.parse("1960-05-25"), ClearanceLevel.SUPERVISOR, "Purchasing leader"));
    }

    @Override
    public void save(Employee employee) {
        int latestEmployeeId = employees.isEmpty() ? 0 : employees.get(employees.size()-1).getId();
        employee.setId(latestEmployeeId + 1);
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Employee> findAll() {
        return employees;
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
