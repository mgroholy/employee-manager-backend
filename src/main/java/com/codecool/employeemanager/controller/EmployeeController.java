package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.model.Status;
import com.codecool.employeemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam Map<String, String> params) {
        List<String> validQueryTypes = Arrays.asList("department", "id", "name", "email", "showInactive");
        for(String param: params.keySet()){
            if(!validQueryTypes.contains(param)){
                throw new IllegalArgumentException("Query type not supported.");
            }
        }
        List<Employee> employees = employeeService.findAllEmployees();
        if (params.containsKey("department")) {
            if (params.get("department").toLowerCase().equals("all")) {
                employees = employeeService.findAllEmployees();
            } else {
                employees = employeeService.findByDepartment(params.get("department"));
            }
        } else if (params.containsKey("id")) {
            employees = Collections.singletonList(employeeService.findEmployeeById(Integer.parseInt(params.get("id"))));
        } else if (params.containsKey("name")) {
            employees = employeeService.findAllByName(params.get("name"));
        } else if (params.containsKey("email")) {
            employees = Collections.singletonList(employeeService.findEmployeeByEmail(params.get("email")));
        }
        if(params.containsKey("showInactive")) {
            if(params.get("showInactive").equals("true")){
                return employees;
            }
        }
        List<Employee> activeEmployees = employees.stream().filter(employee -> employee.getStatus() == Status.ACTIVE).collect(Collectors.toList());
        if(activeEmployees.size() == 0 && employees.size() > 0){
            throw new NoSuchElementException("No active employee found.");
        }
        return activeEmployees;

    }

    @PostMapping("/employees")
    public Employee createNewEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @PatchMapping("/employees/{id}/partial-update")
    public void setPartialEmployeeUpdate(@PathVariable int id, @RequestBody Map<String, String> data) {
        employeeService.updateEmployeeDetail(id, data);
    }

    @DeleteMapping("/employees/{id}/delete")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/levels")
    public ClearanceLevel[] getAllClearanceLevels() {
        return ClearanceLevel.values();
    }

    @GetMapping("/statuses")
    public Status[] getAllStatuses(){
        return Status.values();
    }

}
