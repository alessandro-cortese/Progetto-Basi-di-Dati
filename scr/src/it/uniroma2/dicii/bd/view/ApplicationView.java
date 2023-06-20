package it.uniroma2.dicii.bd.view;

import java.util.Scanner;

public class ApplicationView {
    public static int showApplicationView(){
        System.out.println("");
        System.out.println("ASTE ONLINE");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("Cova vuoi fare?");
            System.out.println("1) Registrazione");
            System.out.println("2) Login");
            choice = input.nextInt();
            if (choice == 1 || choice == 2){
                break;
            }
            System.out.println("Operazione invalida!");
        }

        return choice;
    }
}
