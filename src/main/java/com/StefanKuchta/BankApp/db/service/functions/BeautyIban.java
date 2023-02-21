package com.StefanKuchta.BankApp.db.service.functions;

public class BeautyIban {
    public static String beautyIban(String iban) {

        return iban.substring(0, 4) + " " // pridá "SK22 "
                + iban.substring(4, 8) + " " // pridá "7700 "
                + iban.substring(8, 12) + " " // pridá "0000 "
                + iban.substring(12, 16) + " " // pridá "0085 "
                + iban.substring(16, 20) + " " // pridá "4612 "
                + iban.substring(20);
    }
}
