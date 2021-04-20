package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(String departmentName);

}
