package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public Department addNewDepartment(Department department){
        Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
        if(departmentOptional.isPresent()){
            throw new IllegalArgumentException("Department with the name \"" + department.getName() + "\" already exists.");
        }
        department.setEmployees(new HashSet<>());
        departmentRepository.save(department);
        return department;
    }

    public void deleteDepartment(int departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
