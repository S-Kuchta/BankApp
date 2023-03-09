package com.StefanKuchta.BankApp.db.service.api.response;


import java.util.Objects;

public class LogInUserResponse {
    private final boolean successful;
    private String errorMessage;


    public LogInUserResponse(boolean successful, String errorMessage) {
        this.successful = successful;
        this.errorMessage = errorMessage;
    }

    public LogInUserResponse(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInUserResponse that = (LogInUserResponse) o;
        return successful == that.successful && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successful, errorMessage);
    }
}
