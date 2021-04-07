package com.codecool.employeemanager.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
    private String message;

    public ApiError(HttpStatus httpStatus, LocalDateTime timeStamp, String message) {
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
