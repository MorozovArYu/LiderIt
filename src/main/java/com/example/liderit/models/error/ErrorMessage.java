package com.example.liderit.models.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String message;
    private LocalDateTime date;
    private String description;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, LocalDateTime date, String description) {
        this.message = message;
        this.date = date;
        this.description = description;
    }

    public ErrorMessage(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
