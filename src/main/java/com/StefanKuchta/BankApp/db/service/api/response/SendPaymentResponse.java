package com.StefanKuchta.BankApp.db.service.api.response;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class SendPaymentResponse {

    @NonNull
    private final boolean success;
    @Nullable
    private String errorMessage;

    public SendPaymentResponse(@NonNull boolean success) {
        this.success = success;
    }

    public SendPaymentResponse(@NonNull boolean success, @Nullable String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPaymentResponse that = (SendPaymentResponse) o;
        return success == that.success && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorMessage);
    }
}
