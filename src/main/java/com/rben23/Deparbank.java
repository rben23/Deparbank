package com.rben23;

public class Deparbank {
    public static void main(String[] args){
        BankAccount bankAccount = new BankAccount();
        bankAccount.BankAccount("ES000000000", "Nombre", 1000);

        OptionMenu optionMenu = new OptionMenu();
        optionMenu.mnuOption(bankAccount);
    }
}