package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class EmpRepImplementation implements EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmpRepImplementation() {
        // for testing purposes
        save(new Employee("John Doe", "john@doe.com", new Department("IT"), "36301234567", LocalDate.parse("1970-02-03"), ClearanceLevel.USER, "Developer"));
        save(new Employee("Jane Doe", "jane@doe.com", new Department("HR"), "36305671234", LocalDate.parse("1985-10-10"), ClearanceLevel.ADMIN, "Recruiter"));
        save(new Employee("Jack Smith", "jack@smith.com", new Department("Sales"), "36309876543", LocalDate.parse("1960-05-25"), ClearanceLevel.SUPERVISOR, "Purchasing leader"));
    }

    @Override
    public void save(Employee employee) {
        int latestEmployeeId = employees.isEmpty() ? 0 : employees.get(employees.size() - 1).getId();
        employee.setId(latestEmployeeId + 1);
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {
        int id = employee.getId();
        deleteById(id);
        employees.add(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employeeToBeDeleted = employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee not found by id - " + id));
        employees.remove(employeeToBeDeleted);
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee not found by id - " + id));
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Employee foundEmployee =  employees.stream().filter(employee -> employee.getEmail().equals(email)).findFirst().orElse(null);
        return Optional.ofNullable(foundEmployee);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employees.stream().filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        return employees.stream().filter(employee -> employee.getDepartment().getName().equals(department)).collect(Collectors.toList());
    }
}
