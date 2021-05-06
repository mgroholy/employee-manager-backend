package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Position;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PositionSerializer extends JsonSerializer<Position> {
    @Override
    public void serialize(Position position, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", position.getId());
        jsonGenerator.writeStringField("name", position.getName());
        jsonGenerator.writeNumberField("employeeCount", position.getEmployees().size());
        jsonGenerator.writeEndObject();
    }
}
