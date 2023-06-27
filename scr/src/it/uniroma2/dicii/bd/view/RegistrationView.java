package it.uniroma2.dicii.bd.view;

import java.util.Scanner;

public class RegistrationView {

    public static int getTypeOfRegistration(){

        int type;
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Come ti vuoi registrare: ");
        System.out.println("1) Utente");
        System.out.println("2) Amministratore");
        System.out.println("");
        System.out.println("Inserisci il tipo di utente:");
        type = input.nextInt();

        if(type < 1 || type > 2){
            System.out.println("Scelta invalida!");
            System.exit(1);
        }

        return type;
    }

}
