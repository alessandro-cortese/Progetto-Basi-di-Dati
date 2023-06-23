package it.uniroma2.dicii.bd.view;

import java.io.IOException;
import java.util.Scanner;

public class UtenteHomeScreenView {

    public static int showHomeScreen() throws IOException {

        int choise = 0;
        System.out.println("");
        System.out.println("Benvenuto nella tua home screen!");
        System.out.println("Cosa vuoi fare?");
        System.out.println("1) Visaulizza aste aperte");
        System.out.println("2) Visualizza aste aperte dove hai fatto offerte");
        System.out.println("3) Fai un'offerta per un oggetto in asta");
        System.out.println("4) Visualizza gli oggetti che hai vinto nella aste");
        System.out.println("5) Esci");
        System.out.println("");
        Scanner input = new Scanner(System.in);

        while(true){

            System.out.println("Inserisci il codice di cosa vuoi fare: ");
            choise = input.nextInt();
            if(choise >= 1 && choise <= 5){
                break;
            }
            System.out.println("Codice non valido");
        }

        return choise;
    }

}
