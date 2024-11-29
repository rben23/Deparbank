package com.rben23;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Movements {

    private BankAccount account = new BankAccount();

    protected Movements(String iban, String holder, double balance, List<String> history) {
        account.BankAccount(iban, holder, balance, history);
    }

    protected void saveMovements() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BankAccount bankAccount = gson.fromJson(jsonSaveStructure(account), BankAccount.class);
        String json = gson.toJson(bankAccount);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveDir(account)))) {
            bufferedWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected BankAccount uploadMovements() {
        Gson gson = new GsonBuilder().create();

        try {
            InputStream inputStream = new FileInputStream(saveDir(account));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            account = gson.fromJson(bufferedReader, BankAccount.class);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }


    protected boolean fileExists() {
        if (new File(saveDir(account)).exists()) {
            return true;
        } else {
            return false;
        }
    }

    private String saveDir(BankAccount account) {
        return "movimientos" + "_" + account.getIban() + ".json";
    }

    private String jsonSaveStructure(BankAccount account) {
        return String.format(Locale.ENGLISH, "{\"iban\":\"%s\",\"holder\":\"%s\",\"balance\":\"%.2f\",\"history\":%s}",
                account.getIban(), account.getHolder(), account.getBalance(), historyToGson(account));
    }

    private String historyToGson(BankAccount account) {
        Gson gson = new Gson();
        return gson.toJson(account.getHistory());
    }
}
