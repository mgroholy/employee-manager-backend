package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.model.Position;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    private Employee testEmployee;
    private Department testDepartment;
    private Position testPosition;

    @BeforeEach
    void setup() {
        testDepartment = Department.builder().name("TEST").build();

        departmentRepository.save(testDepartment);

        testPosition = Position.builder().name("test").build();

        positionRepository.save(testPosition);

         testEmployee = Employee.builder()
                .name("Test Employee")
                .email("test@employee.com")
                .dateOfBirth(LocalDate.of(1990,1, 1))
                .clearanceLevel(ClearanceLevel.ADMIN)
                .department(testDepartment)
                .phoneNumber("12345678910")
                .position(testPosition)
                .build();
        employeeRepository.save(testEmployee);
    }

    @Test
    void findOneEmployeeByEmail() {
        assertEquals(Optional.of(testEmployee), employeeRepository.findByEmail("test@employee.com"));
    }

    @Test
    void findEmployeesByNameContaining() {
        Employee anotherTestEmployee = Employee.builder()
                .name("Another Test Employee")
                .email("anothertest@employee.com")
                .dateOfBirth(LocalDate.of(1980,1, 1))
                .clearanceLevel(ClearanceLevel.USER)
                .department(testDepartment)
                .phoneNumber("19876543210")
                .position(testPosition)
                .build();
        Employee anotherEmployee = Employee.builder()
                .name("Another Employee")
                .email("another@employee.com")
                .dateOfBirth(LocalDate.of(1970,1, 1))
                .clearanceLevel(ClearanceLevel.SUPERVISOR)
                .department(testDepartment)
                .phoneNumber("54321019876")
                .position(testPosition)
                .build();
        employeeRepository.saveAll(Lists.newArrayList(anotherTestEmployee, anotherEmployee));

        List<Employee> filteredEmployees = employeeRepository.findByNameContaining("Test");
        assertThat(filteredEmployees).containsExactlyInAnyOrder(testEmployee, anotherTestEmployee);
    }

    @Test
    void findEmployeesByDepartmentName() {
        Department anotherDepartment = Department.builder().name("ANOTHER").build();
        departmentRepository.save(anotherDepartment);

        Employee anotherTestEmployee = Employee.builder()
                .name("Another Test Employee")
                .email("anothertest@employee.com")
                .dateOfBirth(LocalDate.of(1980,1, 1))
                .clearanceLevel(ClearanceLevel.USER)
                .department(anotherDepartment)
                .phoneNumber("19876543210")
                .position(testPosition)
                .build();
        Employee anotherEmployee = Employee.builder()
                .name("Another Employee")
                .email("another@employee.com")
                .dateOfBirth(LocalDate.of(1970,1, 1))
                .clearanceLevel(ClearanceLevel.SUPERVISOR)
                .department(anotherDepartment)
                .phoneNumber("54321019876")
                .position(testPosition)
                .build();
        employeeRepository.saveAll(Lists.newArrayList(anotherTestEmployee, anotherEmployee));

        List<Employee> filteredEmployees = employeeRepository.findByDepartmentName("ANOTHER");
        assertThat(filteredEmployees)
                .hasSize(2)
                .containsExactlyInAnyOrder(anotherEmployee, anotherTestEmployee);
    }
}