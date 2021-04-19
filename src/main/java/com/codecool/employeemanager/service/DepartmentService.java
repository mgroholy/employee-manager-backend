package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department findByName(String name) {
        return departmentRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Department not found by name - " + name));
    }
}
