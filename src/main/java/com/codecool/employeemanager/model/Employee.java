package com.codecool.employeemanager.model;

import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String department;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private ClearanceLevel clearanceLevel;
    private String position;

    public Employee() {
    }

    public Employee(String name, String email, String department, String phoneNumber, LocalDate dateOfBirth, ClearanceLevel clearanceLevel, String position) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.clearanceLevel = clearanceLevel;
        this.position = position;
    }

    public Employee(int id, String name, String email, String department, String phoneNumber, LocalDate dateOfBirth, ClearanceLevel clearanceLevel, String position) {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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
