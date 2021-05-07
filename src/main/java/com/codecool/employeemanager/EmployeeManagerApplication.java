package com.codecool.employeemanager;

import com.codecool.employeemanager.model.*;
import com.codecool.employeemanager.repository.DepartmentRepository;
import com.codecool.employeemanager.repository.EmployeeRepository;
import com.codecool.employeemanager.repository.PositionRepository;
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
    private PositionRepository positionRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeManagerApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, UserRepository userRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
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

            Position developer = Position.builder().name("Developer").build();
            Position mentor = Position.builder().name("Mentor").build();
            Position recruiter = Position.builder().name("Recruiter").build();
            Position director = Position.builder().name("Director").build();
            Position assistant = Position.builder().name("Assistant").build();
            Position manager = Position.builder().name("Manager").build();
            positionRepository.saveAll(Arrays.asList(developer, mentor, recruiter, director, assistant, manager));

            Employee john = Employee.builder().name("John Doe").email("john@doe.com").department(IT).phoneNumber("36301234567").dateOfBirth(LocalDate.parse("1970-02-03")).clearanceLevel(ClearanceLevel.USER).position(developer).status(Status.ACTIVE).dateOfHire(LocalDate.of(2020, 2, 15)).build();
            Employee jane = Employee.builder().name("Jane Doe").email("jane@doe.com").department(HR).phoneNumber("36305671234").dateOfBirth(LocalDate.parse("1985-10-10")).clearanceLevel(ClearanceLevel.ADMIN).position(recruiter).status(Status.ACTIVE).dateOfHire(LocalDate.of(2018, 9, 1)).build();
            Employee jack = Employee.builder().name("Jack Smith").email("jack@smith.com").department(SALES).phoneNumber("36309876543").dateOfBirth(LocalDate.parse("1960-05-25")).clearanceLevel(ClearanceLevel.SUPERVISOR).position(director).status(Status.ACTIVE).dateOfHire(LocalDate.of(2005, 6, 10)).build();
            Employee kate = Employee.builder().name("Kate Williams").email("kate@williams.com").department(IT).phoneNumber("36702345678").dateOfBirth(LocalDate.parse("1974-11-23")).clearanceLevel(ClearanceLevel.USER).position(mentor).status(Status.ACTIVE).dateOfHire(LocalDate.of(2012, 12, 1)).build();
            Employee matt = Employee.builder().name("Matt Davis").email("matt@davis.com").department(HR).phoneNumber("36209871234").dateOfBirth(LocalDate.parse("1980-01-10")).clearanceLevel(ClearanceLevel.ADMIN).position(director).status(Status.ACTIVE).dateOfHire(LocalDate.of(2016, 9, 20)).build();
            Employee dan = Employee.builder().name("Dan Brown").email("dan@brown.com").department(SALES).phoneNumber("36203679834").dateOfBirth(LocalDate.parse("1964-06-22")).clearanceLevel(ClearanceLevel.SUPERVISOR).position(manager).status(Status.INACTIVE).dateOfHire(LocalDate.of(2010, 10, 15)).dateOfTermination(LocalDate.of(2020, 11, 30)).build();
            Employee sam = Employee.builder().name("Sam Miller").email("sam@miller.com").department(IT).phoneNumber("36702349876").dateOfBirth(LocalDate.parse("1975-07-20")).clearanceLevel(ClearanceLevel.SUPERVISOR).position(manager).status(Status.ACTIVE).dateOfHire(LocalDate.of(2015, 4, 1)).build();
            Employee robert = Employee.builder().name("Robert Johnson").email("robert@johnson.com").department(SALES).phoneNumber("36201239876").dateOfBirth(LocalDate.parse("2000-03-06")).clearanceLevel(ClearanceLevel.USER).position(assistant).status(Status.INACTIVE).dateOfHire(LocalDate.of(2019, 7, 3)).dateOfTermination(LocalDate.of(2020, 5, 10)).build();
            Employee diana = Employee.builder().name("Diana Jonas").email("diana@jonas.com").department(HR).phoneNumber("36303356789").dateOfBirth(LocalDate.parse("1998-09-13")).clearanceLevel(ClearanceLevel.USER).position(assistant).status(Status.ACTIVE).dateOfHire(LocalDate.of(2021, 2, 1)).build();
            Employee julia = Employee.builder().name("Julia Taylor").email("julia@taylor.com").department(SALES).phoneNumber("36205673398").dateOfBirth(LocalDate.parse("1989-04-15")).clearanceLevel(ClearanceLevel.SUPERVISOR).position(mentor).status(Status.INACTIVE).dateOfHire(LocalDate.of(2017, 3, 18)).dateOfTermination(LocalDate.of(2019, 8, 15)).build();
            employeeRepository.saveAll(Arrays.asList(john, jane, jack, kate, matt, dan, sam, robert, diana, julia));

            UserModel johnUser = UserModel.builder().email("john@doe.com").employee(john).password(passwordEncoder.encode("john")).levels(List.of(ClearanceLevel.USER)).build();
            UserModel jackUser = UserModel.builder().email("jack@smith.com").employee(jack).password(passwordEncoder.encode("jack")).levels(List.of(ClearanceLevel.USER, ClearanceLevel.SUPERVISOR)).build();
            UserModel mattUser = UserModel.builder().email("matt@davis.com").employee(matt).password(passwordEncoder.encode("matt")).levels(List.of(ClearanceLevel.USER, ClearanceLevel.SUPERVISOR, ClearanceLevel.ADMIN)).build();
            userRepository.saveAll(Arrays.asList(johnUser, jackUser, mattUser));

        };
    }
}
