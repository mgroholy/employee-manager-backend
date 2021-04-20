package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void findDepartmentByName() {
        Department testDepartment = Department.builder().name("TEST").build();
        departmentRepository.save(testDepartment);

        assertEquals(Optional.of(testDepartment), departmentRepository.findByName("TEST"));
    }
}