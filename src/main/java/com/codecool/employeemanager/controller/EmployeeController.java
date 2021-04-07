package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
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

    @PostMapping("/employees")
    public Employee createNewEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = { RequestMethod.GET, RequestMethod.DELETE })
    public Employee getEmployeeById(@PathVariable int id, HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return employeeService.findEmployeeById(id);
        } else {
            employeeService.deleteEmployeeById(id);
            return null;
        }
    }
}
