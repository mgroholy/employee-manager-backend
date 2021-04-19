package com.codecool.employeemanager;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import com.codecool.employeemanager.repository.DepartmentRepository;
import com.codecool.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class EmployeeManagerApplication {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeManagerApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagerApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Department IT = Department.builder().name("IT").build();
            Department HR = Department.builder().name("HR").build();
            Department SALES = Department.builder().name("Sales").build();
            departmentRepository.saveAll(Arrays.asList(IT, HR, SALES));

            Employee john = Employee.builder().name("John Doe").email("john@doe.com").department(IT).phoneNumber("36301234567").dateOfBirth(LocalDate.parse("1970-02-03")).clearanceLevel(ClearanceLevel.USER).position("Developer").build();
            Employee jane = Employee.builder().name("Jane Doe").email("jane@doe.com").department(HR).phoneNumber("36305671234").dateOfBirth(LocalDate.parse("1985-10-10")).clearanceLevel(ClearanceLevel.ADMIN).position("Recruiter").build();
            Employee jack = Employee.builder().name("Jack Smith").email("jack@smith.com").department(SALES).phoneNumber("36309876543").dateOfBirth(LocalDate.parse("1960-05-25")).clearanceLevel(ClearanceLevel.SUPERVISOR).position("Purchasing leader").build();
            employeeRepository.saveAll(Arrays.asList(john, jane, jack));
        };
    }
}
