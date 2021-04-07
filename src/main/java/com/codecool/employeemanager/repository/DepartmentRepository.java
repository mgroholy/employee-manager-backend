package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    void save(Department department);
    Optional<Department> findByName(String departmentName);
    List<Department> findAll();

}
