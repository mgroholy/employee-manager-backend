package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.*;
import com.codecool.employeemanager.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    private static EmployeeService mockEmployeeService;
    private static EmployeeController employeeController;
    private static final Department IT = Department.builder().name("IT").build();
    private static final Department HR = Department.builder().name("HR").build();
    private static final Department SALES = Department.builder().name("Sales").build();
    private static Employee employeeIT;
    private static Employee employeeHR;
    private static Employee employeeSales;
    private static Employee inactiveEmployee;
    private static final Position TEST_POSITION = Position.builder().name("test").build();


    @BeforeAll
    public static void setup(){
        mockEmployeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(mockEmployeeService);
    }

    @BeforeAll
    public static void createTestEmployees(){
        employeeIT = Employee.builder()
                .id(0)
                .name("Test employee 2")
                .email("test1@gmail.com")
                .clearanceLevel(ClearanceLevel.ADMIN)
                .dateOfBirth(LocalDate.of(1989,1,1))
                .department(IT)
                .phoneNumber("12345678910")
                .position(TEST_POSITION)
                .status(Status.ACTIVE)
                .build();
        employeeHR = Employee.builder()
                .id(1)
                .name("Test employee 2")
                .email("test2@gmail.com")
                .clearanceLevel(ClearanceLevel.ADMIN)
                .dateOfBirth(LocalDate.of(1990,1,1))
                .department(HR)
                .phoneNumber("12345678910")
                .position(TEST_POSITION)
                .status(Status.ACTIVE)
                .build();
        employeeSales = Employee.builder()
                .id(2)
                .name("Test employee 3")
                .email("test3@gmail.com")
                .clearanceLevel(ClearanceLevel.ADMIN)
                .dateOfBirth(LocalDate.of(1991,1,1))
                .department(SALES)
                .phoneNumber("12345678910")
                .position(TEST_POSITION)
                .status(Status.ACTIVE)
                .build();
        inactiveEmployee = Employee.builder()
                .id(3)
                .name("Test employee 4")
                .email("test4@gmail.com")
                .clearanceLevel(ClearanceLevel.ADMIN)
                .dateOfBirth(LocalDate.of(1992,1,1))
                .department(SALES)
                .phoneNumber("12345678910")
//                .position("test")
                .status(Status.INACTIVE)
                .build();
    }

    @Test
    public void getEmployees_noRequestParam_returnAllEmployees(){
        List<Employee> allEmployees = Arrays.asList(employeeIT, employeeHR, employeeSales, inactiveEmployee);
        when(mockEmployeeService.findAllEmployees()).thenReturn(allEmployees);
        List<Employee> requestResult = employeeController.getEmployees(new HashMap<>());
        assertEquals(Arrays.asList(employeeIT, employeeHR, employeeSales), requestResult);
    }

    @Test
    public void getEmployees_paramDepartmentAll_returnAllEmployees(){
        List<Employee> allEmployees = Arrays.asList(employeeIT, employeeHR, employeeSales, inactiveEmployee);
        when(mockEmployeeService.findAllEmployees()).thenReturn(allEmployees);
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("department", "all");
        List<Employee> requestResult = employeeController.getEmployees(requestParams);
        assertEquals(Arrays.asList(employeeIT,employeeHR,employeeSales), requestResult);
    }
    @Test
    public void getEmployees_paramDepartmentIT_returnsEmployeeWithDepartmentIT(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("department", "IT");
        when(mockEmployeeService.findByDepartment(requestParams.get("department"))).thenReturn(Arrays.asList(employeeIT));
        assertEquals(employeeIT, employeeController.getEmployees(requestParams).get(0));
    }
    @Test
    public void getEmployees_paramDepartmentHR_returnsEmployeeWithDepartmentHR(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("department", "HR");
        when(mockEmployeeService.findByDepartment(requestParams.get("department"))).thenReturn(Arrays.asList(employeeHR));
        assertEquals(employeeHR, employeeController.getEmployees(requestParams).get(0));
    }

    @Test
    public void getEmployees_paramDepartmentSales_returnsEmployeeWithDepartmentSales(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("department", "Sales");
        when(mockEmployeeService.findByDepartment(requestParams.get("department"))).thenReturn(Arrays.asList(employeeSales));
        assertEquals(employeeSales, employeeController.getEmployees(requestParams).get(0));
    }

    @Test
    public void getEmployees_paramValidEmail_returnsEmployeeWithThatEmail(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("email", employeeIT.getEmail());
        when(mockEmployeeService.findEmployeeByEmail(requestParams.get("email"))).thenReturn(employeeIT);
        assertEquals(employeeIT, employeeController.getEmployees(requestParams).get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "invalid", "not in handled params"})
    public void getEmployees_invalidParam_throwsIllegalArgumentException(String param){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(param, "");
        assertThrows(IllegalArgumentException.class, () -> employeeController.getEmployees(requestParams));
    }

    @Test
    public void getEmployees_paramValidId_returnsEmployeeWithId(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("id", Integer.toString(employeeHR.getId()));
        when(mockEmployeeService.findEmployeeById(any(Integer.class))).thenReturn(employeeHR);
        assertEquals(employeeHR, employeeController.getEmployees(requestParams).get(0));
    }

    @Test
    public void getEmployees_paramValidName_returnsEmployeeWithName(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("name", employeeSales.getName());
        when(mockEmployeeService.findAllByName(employeeSales.getName())).thenReturn(Arrays.asList(employeeSales));
        assertEquals(employeeSales, employeeController.getEmployees(requestParams).get(0));
    }

    @Test
    public void getEmployees_paramShowInactiveTrue_returnsAllEmployees(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("showInactive", "true");
        when(mockEmployeeService.findAllEmployees()).thenReturn(Arrays.asList(employeeIT,employeeHR,employeeSales,inactiveEmployee));
        assertEquals(4, employeeController.getEmployees(requestParams).size());
    }

    @Test
    public void getEmployees_paramNoActiveEmployeeFound_throwsNoSuchElementException(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("name", inactiveEmployee.getName());
        when(mockEmployeeService.findAllByName(inactiveEmployee.getName())).thenReturn(Arrays.asList(inactiveEmployee));
        assertThrows(NoSuchElementException.class, () -> employeeController.getEmployees(requestParams));
    }
    @Test
    public void getEmployees_paramShowInactiveFoundEmployee_returnsFoundInactiveEmployee(){
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("name", inactiveEmployee.getName());
        requestParams.put("showInactive", "true");
        when(mockEmployeeService.findAllByName(inactiveEmployee.getName())).thenReturn(Arrays.asList(inactiveEmployee));
        assertEquals(inactiveEmployee, employeeController.getEmployees(requestParams).get(0));
    }












}