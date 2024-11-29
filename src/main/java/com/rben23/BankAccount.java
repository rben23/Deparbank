package com.rben23;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String iban;
    private String holder;
    private double balance;
    private List<String> history;

    protected void BankAccount(String iban, String holder, double balance) {
        this.iban = iban;
        this.holder = holder;
        this.balance = balance;
        history = new ArrayList<>();
    }

    protected void uploadBankAccount() {
        BankAccount newAccount = getMovements().uploadMovements();
        this.iban = newAccount.getIban();
        this.holder = newAccount.getHolder();
        this.balance = newAccount.getBalance();
        this.history = newAccount.getHistory();
    }

    protected void BankAccount(String iban, String holder, double balance, List<String> history) {
        this.iban = iban;
        this.holder = holder;
        this.balance = balance;
        this.history = history;
    }

    protected String getIban() {
        return iban;
    }

    protected String getHolder() {
        return holder;
    }

    protected double getBalance() {
        return balance;
    }

    protected List<String> getHistory() {
        return history;
    }

    protected void income(double amount) {
        if (amount >= 0) {
            this.balance += amount;
            history.add("→ Ingreso" + " " + amount);
            getMovements().saveMovements();

            haciendaNotification(amount);
        }
        toString();
    }

    protected void withdrawal(double amount) {
        double newBalance = this.balance - amount;
        if (amount >= 0 && newBalance > -50) {
            this.balance = newBalance;
            history.add("← Retirada" + " " + amount);
            getMovements().saveMovements();

            negativeBalance();
            haciendaNotification(amount);
        }
        toString();
    }

    protected void loginAccount() {
        if (getMovements().fileExists()) {
            uploadBankAccount();
        }
    }

    private Movements getMovements() {
        return new Movements(this.iban, this.holder, this.balance, this.history);
    }

    private void negativeBalance() {
        if (balance < 0) {
            System.out.println(new OptionMenu().MSG_NEGATIVE_BALANCE);
        }
    }

    private static void haciendaNotification(double amount) {
        if (amount >= 3000) {
            System.out.println(new OptionMenu().MSG_HACIENDA_NOTIFICATION);
        }
    }

    @Override
    public String toString() {
        return "- IBAN: " + this.iban +
                "\n- Titular: " + this.holder +
                "\n- Saldo: " + this.balance;
    }

    public String historyToString() {
        String historyString = "";
        for (int i = 0; i < history.size(); i++) {
            historyString += history.get(i) + "\n";
        }
        return historyString;
    }
}
