package com.codecool.employeemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;
import java.time.LocalDate;

public class Employee {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Phone number")
    private String phoneNumber;
    @JsonProperty("Date of birth")
    private LocalDate dateOfBirth;
    @JsonProperty("Department")
    private Department department;
    @JsonProperty("Position")
    private String position;
    @JsonProperty("Clearance level")
    private ClearanceLevel clearanceLevel;

    public Employee() {
    }

    public Employee(String name, String email, Department department, String phoneNumber, LocalDate dateOfBirth, ClearanceLevel clearanceLevel, String position) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.clearanceLevel = clearanceLevel;
        this.position = position;
    }

    public Employee(int id, String name, String email, Department department, String phoneNumber, LocalDate dateOfBirth, ClearanceLevel clearanceLevel, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.clearanceLevel = clearanceLevel;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ClearanceLevel getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(ClearanceLevel clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
