package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.Offerta;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UtenteHomeScreenView {

    public static int showHomeScreen() throws IOException {

        int choise;
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

            System.out.println("Inserisci il codice: ");
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
        if(listaOggetti == null || listaOggetti.getSize() < 1){
            System.out.println("\nNon è presente nessuna asta aperta in questo momento! ");
        }else {
            tablePrinter.setHeaders("Codice", "Descrizione", "Stato", "Descrizione Dimensioni", "Prezzo di base",
                                    "Numero Offerte", "Data Fine Asta", "Orario Fine Asta",
                                    "Importo della massima offerta", "Categoria");

                for (int i = 0; i < listaOggetti.getSize(); i++) {

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
                System.out.println("");
                tablePrinter.print();

            }
        }


    public static void vediAsteAperteConOfferte(ListaOggetti listaOggetti){

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if(listaOggetti == null || listaOggetti.getSize() < 1){
            System.out.println("\nNon ci sono offerte per aste aperte in questo momento.");
        }else{

            tablePrinter.setHeaders("Codice Oggetto", "Descrizione", "Stato", "Descrizione Dimensioni",
                                    "Prezzo di Base", "Data Fine Asta", "Ora fine asta",
                                    "Valore della massima offerta");

            for(int i = 0; i < listaOggetti.getSize(); i++){
                tablePrinter.addRow(String.valueOf(listaOggetti.getList().get(i).getCodice()),
                                    String.valueOf(listaOggetti.getList().get(i).getDescrizione()),
                                    String.valueOf(listaOggetti.getList().get(i).getStato()),
                                    String.valueOf(listaOggetti.getList().get(i).getDescrizioneDimensioni()),
                                    String.valueOf(listaOggetti.getList().get(i).getPrezzoDiBase()),
                                    String.valueOf(listaOggetti.getList().get(i).getDataFineAsta()),
                                    String.valueOf(listaOggetti.getList().get(i).getOrarioInizioAsta()),
                                    String.valueOf(listaOggetti.getList().get(i).getValoreMassimaOfferta())
                        );
            }
            System.out.println("");
            tablePrinter.print();

        }

    }

    public static void vediOggettiAcquistati(ListaOggetti listaOggetti){

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if(listaOggetti == null || listaOggetti.getSize() < 1){
            System.out.println("\nNon hai ancora vinto nessuna asta.");
        }else{

            tablePrinter.setHeaders("Descrizione", "Stato", "Descrizione Dimensioni", "Categoria",
                                    "Prezzo di Vendita");

            for(int i = 0; i < listaOggetti.getSize(); i++){

                tablePrinter.addRow(String.valueOf(listaOggetti.getList().get(i).getDescrizione()),
                                    String.valueOf(listaOggetti.getList().get(i).getStato()),
                                    String.valueOf(listaOggetti.getList().get(i).getDescrizioneDimensioni()),
                                    String.valueOf(listaOggetti.getList().get(i).getCategoria().getNome()),
                                    String.valueOf(listaOggetti.getList().get(i).getPrezzoDiVendita()));
            }

        }
        System.out.println("");
        tablePrinter.print();

    }

    public static Offerta getOffertaInfo(User user) throws IOException{

        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Offerta offerta = new Offerta();
        offerta.setUtente(user.getCodiceFiscale());

        int choise;

        System.out.println("");
        System.out.println("Inserisci il codice dell'oggetto su cui vuoi fare un'offerta: ");
        offerta.setOggetto(reader.readLine());
        System.out.println("Inserisci l'importo dell'offerta: ");
        offerta.setImporto(input.nextFloat());
        boolean flag;

        do{
            System.out.println("Vuoi impostare la controfferta automatica su questo oggetto?");
            System.out.println("1) Sì");
            System.out.println("2) No");
            choise = input.nextInt();

            if (choise < 1 || choise > 2) {
                System.out.println("Scelta non valida!");
                flag = true;
            }else{
                flag = false;
            }


        }while(flag);

        if(choise == 1) {

            System.out.println("Insersci il valore dell'importo massimo per la controfferta automatica:");
            offerta.setValoreMassimoImporto(input.nextFloat());

        }

        return offerta;
    }

    public static void exit(){
        System.out.println("");
        System.out.println("A presto!");
    }

}
