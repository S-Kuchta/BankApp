package com.StefanKuchta.BankApp.service.functions;

import java.util.concurrent.ThreadLocalRandom;

public class IbanGenerator {

    public String generateIban() {
        StringBuilder ibanNumber = new StringBuilder();
        ibanNumber.append("SK77 7700 0000 00");
        for (int i = 0; i < 10; i++) {
            int randomNumber = getRandomValue(1,9);
            ibanNumber.append(randomNumber);
        }
        return ibanNumber.toString();
    }

    public int getRandomValue(int Min, int Max) {
        // Get and return the random integer
        // within Min and Max
        return ThreadLocalRandom
                .current()
                .nextInt(Min, Max + 1);
    }
}
