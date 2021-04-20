package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Department;
import com.codecool.employeemanager.model.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class DepartmentSerializer extends JsonSerializer<Department> {


    @Override
    public void serialize(Department department, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", department.getId());
        jsonGenerator.writeStringField("name", department.getName());
        jsonGenerator.writeNumberField("employeeCount", department.getEmployees().size());
        jsonGenerator.writeEndObject();
    }
}
