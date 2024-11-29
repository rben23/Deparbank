package com.rben23;

import java.util.Scanner;

public class OptionMenu {
    Scanner scanner = new Scanner(System.in);

    private String MNU_INITIAL = """
            \nDEPARBANK
            ====================================
            %s
            ------------------------------------
             [ 1 ] Ingresar
             [ 2 ] Retirar
             [ 3 ] Movimientos
            -----------------------------------
             [ 0 ] Salir
            
             Opción...:\s""";

    private String MNU_INCOME = """
            \n→ INCOME
            ====================================
            
             Cantidad...:\s""";

    private String MNU_WITHDRAWAL = """
            \n← WITHDRAWAL
            ====================================
            
             Cantidad...:\s""";

    private String MNU_MOVEMENTS = """
            \n⇄ MOVEMENTS
            ====================================
            %s""";

    protected String MSG_HACIENDA_NOTIFICATION = "! AVISO: Notificación a Hacienda";
    protected String MSG_NEGATIVE_BALANCE = "! AVISO: Cuenta con saldo en negativo";

    public void mnuOption(BankAccount account) {
        account.loginAccount();
        int entrance;
        do {
            System.out.printf(MNU_INITIAL, account.toString());
            entrance = scanner.nextInt();

            switch (entrance) {
                case 1 -> incomeOption(account);
                case 2 -> withdrawalOption(account);
                case 3 -> movementsOption(account);
            }
        } while (entrance != 0);
    }

    private double incomeOption(BankAccount account){
        System.out.printf(MNU_INCOME, account.toString());

        double entrada = scanner.nextDouble();
        account.income(entrada);

        return entrada;
    }

    private void withdrawalOption(BankAccount account){
        System.out.printf(MNU_WITHDRAWAL, account.toString());

        int entrance = scanner.nextInt();
        account.withdrawal(entrance);
    }

    private void movementsOption(BankAccount account) {
        System.out.printf(MNU_MOVEMENTS, account.historyToString());
    }

}
