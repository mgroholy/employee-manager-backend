package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.model.Status;
import com.codecool.employeemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import javax.validation.Valid;

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

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam Map<String, String> params){
        if(params.containsKey("department")){
            if(params.get("department").toLowerCase().equals("all")){
                return employeeService.findAllEmployees();
            }
            return employeeService.findByDepartment(params.get("department"));
        } else {
            if(params.containsKey("id")){
                return Collections.singletonList(employeeService.findEmployeeById(Integer.parseInt(params.get("id"))));
            } else if(params.containsKey("name")){
                return employeeService.findAllByName(params.get("name"));
            } else if(params.containsKey("email")){
                return Collections.singletonList(employeeService.findEmployeeByEmail(params.get("email")))  ;
            }
        }
        return employeeService.findAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createNewEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PutMapping("/employees/{id}/update")
    public void updateEmployee(@Valid @RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{id}/delete")
    public void deleteEmployee(@PathVariable int id){
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
