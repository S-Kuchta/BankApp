package com.StefanKuchta.BankApp.domain;

import org.springframework.lang.NonNull;

public record LogInData(@NonNull String email, @NonNull String password) {
}
