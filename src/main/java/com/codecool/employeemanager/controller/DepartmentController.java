package com.codecool.employeemanager.controller;


import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DepartmentController {


    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    public Department addNewDepartment(@RequestBody Department department){
        departmentService.addNewDepartment(department);
        return department;
    }

}
