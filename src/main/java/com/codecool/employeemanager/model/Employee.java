package com.codecool.employeemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @JsonProperty("ID")
    private int id;

    @NotBlank(message = "The name field is required.")
    @JsonProperty("Name")
    private String name;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "The email field is required.")
    @JsonProperty("Email")
    private String email;

    @Pattern(regexp = "\\d{11}", message = "Phone number should have 11 digits.")
    @NotBlank(message = "The phone number field is required.")
    @JsonProperty("Phone number")
    private String phoneNumber;

    @JsonProperty("Date of birth")
    private LocalDate dateOfBirth;

    @JsonProperty("Department")
    private Department department;

    @NotBlank(message = "The position field is required.")
    @JsonProperty("Position")
    private String position;

    @JsonProperty("Clearance level")
    private ClearanceLevel clearanceLevel;
}
