package com.StefanKuchta.BankApp.db.service.functions;

import java.util.concurrent.ThreadLocalRandom;

public class IbanGenerator {

    public static String generateIban() {
        StringBuilder ibanNumber = new StringBuilder();
        ibanNumber.append("SK227700000000");
        for (int i = 0; i < 10; i++) {
            int randomNumber = getRandomValue(1,9);
            ibanNumber.append(randomNumber);
        }
        return ibanNumber.toString().trim();
    }

    public static int getRandomValue(int Min, int Max) {
        // Get and return the random integer
        // within Min and Max
        return ThreadLocalRandom
                .current()
                .nextInt(Min, Max + 1);
    }
}
