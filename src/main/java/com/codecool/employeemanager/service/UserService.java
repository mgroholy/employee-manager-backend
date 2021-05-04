package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.*;
import com.codecool.employeemanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, EmployeeService employeeService) {
        this.userRepository = userRepository;
        this.employeeService = employeeService;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void addUser(UserDto userData) {
        String email = userData.getEmail();

        if (isEmailUnique(email)) {
            Employee foundEmployee = getEmployeeByEmail(email);

            UserModel user = UserModel.builder()
                    .email(email)
                    .password(passwordEncoder.encode(userData.getPassword()))
                    .levels(getRoles(foundEmployee.getClearanceLevel()))
                    .employee(foundEmployee)
                    .build();

            userRepository.save(user);
        }
    }

    private boolean isEmailUnique(String email) {
        if (userRepository.findByEmail(email).isEmpty()) return true;
        else throw new IllegalArgumentException("Email address already in use.");
    }

    private Employee getEmployeeByEmail(String email) {
        return employeeService.findEmployeeByEmail(email);
    }

    private List<ClearanceLevel> getRoles(ClearanceLevel level) {
        switch (level) {
            case USER: return Collections.singletonList(ClearanceLevel.USER);
            case SUPERVISOR: return Arrays.asList(ClearanceLevel.USER, ClearanceLevel.SUPERVISOR);
            case ADMIN: return Arrays.asList(ClearanceLevel.USER, ClearanceLevel.SUPERVISOR, ClearanceLevel.ADMIN);
            default:
                log.error("User registration error : case " + level + " not found in switch.");
                throw new IllegalArgumentException("An error occurred on our side.");
        }
    }

}
