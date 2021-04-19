package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepRepImplementation implements DepartmentRepository{

    private List<Department> departments;

    public DepRepImplementation() {
        this.departments = new ArrayList<>();
        Department IT = Department.builder().name("IT").build();
        Department HR = Department.builder().name("HR").build();
        Department SALES = Department.builder().name("Sales").build();
        save(IT);
        save(HR);
        save(SALES);
    }

    @Override
    public void save(Department department) {
        int latestEmployeeId = departments.isEmpty() ? 0 : departments.get(departments.size() - 1).getId();
        department.setId(latestEmployeeId + 1);
        departments.add(department);
    }

    @Override
    public Optional<Department> findByName(String departmentName) {
        return departments.stream().filter(department -> department.getName().equals(departmentName)).findFirst();
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }
}
