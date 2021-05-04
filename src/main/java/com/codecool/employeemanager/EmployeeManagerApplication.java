package com.codecool.employeemanager;

import com.codecool.employeemanager.model.*;
import com.codecool.employeemanager.repository.DepartmentRepository;
import com.codecool.employeemanager.repository.EmployeeRepository;
import com.codecool.employeemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EmployeeManagerApplication {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeManagerApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
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

            Employee john = Employee.builder().name("John Doe").email("john@doe.com").department(IT).phoneNumber("36301234567").dateOfBirth(LocalDate.parse("1970-02-03")).clearanceLevel(ClearanceLevel.USER).position("Developer").status(Status.ACTIVE).dateOfHire(LocalDate.of(2020, 2, 15)).build();
            Employee jane = Employee.builder().name("Jane Doe").email("jane@doe.com").department(HR).phoneNumber("36305671234").dateOfBirth(LocalDate.parse("1985-10-10")).clearanceLevel(ClearanceLevel.ADMIN).position("Recruiter").status(Status.ACTIVE).dateOfHire(LocalDate.of(2018, 9, 1)).build();
            Employee jack = Employee.builder().name("Jack Smith").email("jack@smith.com").department(SALES).phoneNumber("36309876543").dateOfBirth(LocalDate.parse("1960-05-25")).clearanceLevel(ClearanceLevel.SUPERVISOR).position("Purchasing leader").status(Status.ACTIVE).dateOfHire(LocalDate.of(2005, 6, 10)).build();
            employeeRepository.saveAll(Arrays.asList(john, jane, jack));

            UserModel jackUser = UserModel.builder().email("jack@smith.com").employee(jack).password(passwordEncoder.encode("password")).levels(List.of(ClearanceLevel.SUPERVISOR)).build();
            userRepository.save(jackUser);

        };
    }
}
