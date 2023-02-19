package com.StefanKuchta.BankApp.db.service.functions;


public class CheckIfIbanBelongToBank {
    public static boolean ibanCheck(String iban) {
        
        String numbers = iban.substring(4, 8);
        String bankCode = "7700";
        return numbers.equals(bankCode);

    }
}
