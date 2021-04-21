package com.codecool.employeemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue
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

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JsonProperty("Department")
    private Department department;

    @NotBlank(message = "The position field is required.")
    @JsonProperty("Position")
    private String position;

    @Enumerated(EnumType.STRING)
    @JsonProperty("Clearance level")
    private ClearanceLevel clearanceLevel;

    @Enumerated(EnumType.STRING)
    @JsonProperty("Status")
    private Status status;

    @JsonProperty("Date of hire")
    private LocalDate dateOfHire;

    @JsonProperty("Date of termination")
    private LocalDate dateOfTermination;

}
