package com.StefanKuchta.BankApp.db.service.functions;

public class BeautyIban {
    public static String beautyIban(String iban) {

        return iban.substring(0, 4) + " "
                + iban.substring(4, 8) + " "
                + iban.substring(8, 12) + " "
                + iban.substring(12, 16) + " "
                + iban.substring(16, 20) + " " 
                + iban.substring(20);
    }
}
