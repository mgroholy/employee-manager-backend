package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static EmployeeRepository mockEmployeeRepository;

    @BeforeAll
    public static void setup(){
        mockEmployeeRepository = mock(EmployeeRepository.class);
        departmentService = mock(DepartmentService.class);
        employeeService = new EmployeeService(mockEmployeeRepository, departmentService);
    }

    @Test
    public void addEmployee_employeeEmailNotInUse_returnsEmployee() {
        when(mockEmployeeRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        Department department = Department.builder().name("HR").build();
        Employee testEmployee = Employee.builder().name("Test Employee").email("test@employee.com")
                .dateOfBirth(LocalDate.of(1990,1, 1))
                .clearanceLevel(ClearanceLevel.ADMIN)
                .department(department)
                .phoneNumber("12345678910")
                .position("test")
                .build();
        when(departmentService.findByName("HR")).thenReturn(new Department("HR"));
        assertEquals(testEmployee, employeeService.addEmployee(testEmployee));
    }

    @Test
    public void addEmployee_employeeEmailInUse_ThrowsIllegalArgumentException(){
        Department department = Department.builder().name("HR").build();
        Employee testEmployee = Employee.builder().name("Test Employee").email("test@employee.com")
                .dateOfBirth(LocalDate.of(1990,1, 1))
                .clearanceLevel(ClearanceLevel.ADMIN)
                .department(department)
                .phoneNumber("12345678910")
                .position("test")
                .build();
        when(mockEmployeeRepository.findByEmail(any(String.class))).thenReturn(Optional.of(testEmployee));
        assertThrows(IllegalArgumentException.class,() -> employeeService.addEmployee(testEmployee));
    }

    @Test
    public void findEmployeeByEmail_EmailNotFound_throwsNoSuchElementException(){
        when(mockEmployeeRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,() -> employeeService.findEmployeeByEmail("email"));
    }

    @Test
    public void findEmployeeByEmail_emailFound_returnsEmployeeWithEmailAddress(){
        Department department = Department.builder().name("HR").build();
        Employee testEmployee = Employee.builder().name("Test Employee").email("test@employee.com")
                .dateOfBirth(LocalDate.of(1990,1, 1))
                .clearanceLevel(ClearanceLevel.ADMIN)
                .department(department)
                .phoneNumber("12345678910")
                .position("test")
                .build();
        when(mockEmployeeRepository.findByEmail("test@employee.com")).thenReturn(Optional.of(testEmployee));
        assertEquals(testEmployee, employeeService.findEmployeeByEmail("test@employee.com"));
    }




}