package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

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

    public static void vediAsteAperte(ListaOggetti listaOggetti){

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if(listaOggetti == null){
            System.out.println("\nNon è presente nessuna asta aperta in questo momento! ");
        }else{
            tablePrinter.setHeaders("Codice", "Descrizione", "Stato", "Descrizione Dimensioni", "Prezzo di base",
                                    "Numero Offerte", "Data Fine Asta", "Orario Fine Asta", "Importo della massima offerta", "Categoria");
            for(int i = 0; i < listaOggetti.getSize(); i++){

                tablePrinter.addRow(listaOggetti.getList().get(i).getCodice(),
                                    listaOggetti.getList().get(i).getDescrizione(),
                                    listaOggetti.getList().get(i).getStato(),
                                    listaOggetti.getList().get(i).getDescrizioneDimensioni(),
                                    listaOggetti.getList().get(i).getPrezzoDiBase().toString(),
                                    listaOggetti.getList().get(i).getNumeroOfferte().toString(),
                                    listaOggetti.getList().get(i).getDataFineAsta().toString(),
                                    listaOggetti.getList().get(i).getOrarioInizioAsta().toString(),
                                    listaOggetti.getList().get(i).getValoreMassimaOfferta().toString(),
                                    listaOggetti.getList().get(i).getCategoria().getNome());

            }

            tablePrinter.print();

        }

    }

    public static void exit(){
        System.out.println("");
        System.out.println("A presto!");
    }

}
