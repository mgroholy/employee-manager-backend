package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepRepImplementation implements DepartmentRepository{

    private List<Department> departments;

    public DepRepImplementation() {
        this.departments = new ArrayList<>();
        save(new Department("IT"));
        save(new Department("HR"));
        save(new Department("Sales"));
    }

    @Override
    public void save(Department department) {
        int latestEmployeeId = departments.isEmpty() ? 0 : departments.get(departments.size() - 1).getId();
        department.setId(latestEmployeeId + 1);
        departments.add(department);
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }
}
