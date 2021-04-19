package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {

    private static DepartmentRepository mockDepartmentRepository;
    private static DepartmentService departmentService;

    @BeforeAll
    private static void setup(){
        mockDepartmentRepository = mock(DepartmentRepository.class);
        departmentService = new DepartmentService(mockDepartmentRepository);
    }

    @Test
    public void addNewDepartment_uniqueDepartmentName_returnsDepartment(){
        when(mockDepartmentRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        Department department = Department.builder().name("Test Department").build();
        assertEquals(department, departmentService.addNewDepartment(department));
    }

    @Test
    public void addNewDepartment_takenDepartmentName_throwsIllegalArgumentException(){
        Department department = Department.builder().name("Test Department").build();
        when(mockDepartmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));
        assertThrows(IllegalArgumentException.class, () -> departmentService.addNewDepartment(department));
    }

}