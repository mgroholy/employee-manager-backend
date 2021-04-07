package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;

import java.util.List;

public interface DepartmentRepository {

    void save(Department department);
    List<Department> findAll();

}
