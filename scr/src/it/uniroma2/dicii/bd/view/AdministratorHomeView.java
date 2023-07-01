package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorHomeView {

    public static int showHomeScreen() throws IOException {

        int choise;
        System.out.println("");
        System.out.println("Benvenuto nella tua home screen!");
        System.out.println("Cosa vuoi fare?");
        System.out.println("1) Visaulizza aste aperte");
        System.out.println("2) Inserisci un oggetto da mettere in asta");
        System.out.println("3) Visualizza le categorie");
        System.out.println("4) Inserisci una categoria");
        System.out.println("5) Modifica il nome di una categoria");
        System.out.println("6) Modifica la macrocategoria di una categoria");
        System.out.println("7) Elimina una macrocategoria da una categoria");
        System.out.println("8) Elimina una categoria");
        System.out.println("9) Esci");
        System.out.println("");
        Scanner input = new Scanner(System.in);

        while(true){

            System.out.println("Inserisci il codice: ");
            choise = input.nextInt();
            if(choise >= 1 && choise <= 9){
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

    public static OggettoInAsta getOggettoInAstaInfo() throws IOException{

        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OggettoInAsta oggettoInAsta = new OggettoInAsta();
        Categoria categoria = new Categoria();

        System.out.println("");
        System.out.println("Inserisci la descrizione dell'oggetto da mettere in asta: ");
        oggettoInAsta.setDescrizione(reader.readLine());
        System.out.println("Inserisci lo stato dell'oggetto da mettere in asta: (in buone condizioni, ...)");
        oggettoInAsta.setStato(reader.readLine());
        System.out.println("Inserisci il prezzo di base d'asta: ");
        oggettoInAsta.setPrezzoDiBase(input.nextFloat());
        System.out.println("Inserisci una descrizione delle dimensioni dell'oggetto da mettere in asta: ");
        oggettoInAsta.setDescrizioneDimensioni(reader.readLine());
        System.out.println("Inserisci la durata dell'asta: (la durata indica i giorni che deve durata l'asta)");
        oggettoInAsta.setDurataAsta(input.nextInt());
        System.out.println("Inserisci la categoria dell'oggetto da mettere in asta: ");
        categoria.setNome(reader.readLine());
        oggettoInAsta.setCategoria(categoria);

        return oggettoInAsta;
    }

    public static Categoria getCategoriaInfo() throws IOException{

        Categoria categoria = new Categoria();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        int choise;

        System.out.println("");
        System.out.println("Inserisci il nome della nuova categoria: ");
        categoria.setNome(reader.readLine());
        System.out.println("La categoria ha una macrocategoria?");
        System.out.println("1) Sì");
        System.out.println("2) No");
        do{

            System.out.println("Inserisci il codice: ");
            choise = input.nextInt();
            if(choise < 1 || choise > 2){
                flag = true;
                System.out.println("Codice non corretto:");
            }else{
                flag = false;
            }

        }while (flag);

        if(choise == 1){
            System.out.println("Inserisci la macrocategoria: ");
            categoria.setMacrocategoria(reader.readLine());
        }

        return categoria;
    }

    public static Categoria getCategoryInfoToDeleteMacroCategory() throws IOException{

        Categoria categoria = new Categoria();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.println("Inserisci il nome della categoria di cui vuoi eliminare la macrocategoria: ");
        categoria.setNome(reader.readLine());

        return categoria;
    }

    public static Categoria getCategoryInfoToDelete() throws IOException{

        Categoria categoria = new Categoria();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.println("Inserisci il nome della categoria che vuoi eliminare: ");
        categoria.setNome(reader.readLine());

        return categoria;
    }

    public static Categoria getCategoryInfoToModifyMacroCategory() throws IOException{
        Categoria categoria = new Categoria();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserisici il nome della categoria di cui cambiare la macrocategoria: ");
        categoria.setNome(reader.readLine());
        System.out.println("Inserisci la macrocategoria da modificare: ");
        categoria.setMacrocategoria(reader.readLine());

        return categoria;
    }

    public static List<Object> getCategoriaInfoToModifyName() throws IOException{

        List<Object> list = new ArrayList<>();
        String nuovoNome;
        Categoria categoria = new Categoria();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il nome della categoria che vuoi modificare: ");
        categoria.setNome(reader.readLine());
        System.out.println("Inserisci il nuovo nome: ");
        nuovoNome = reader.readLine();
        list.add(categoria);
        list.add(nuovoNome);

        return list;
    }

    public static void stampaCategorie(ListaCategorie listaCategorie){

        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if(listaCategorie == null || listaCategorie.getSize() < 1){
            System.out.println("\nNon ci sono categorie.");
        }else{

            tablePrinter.setHeaders("Nome", "Macrocategoria");

            for(int i = 0; i < listaCategorie.getSize(); i++){

                tablePrinter.addRow(listaCategorie.getList().get(i).getNome(),
                                    listaCategorie.getList().get(i).getMacrocategoria());

            }

            System.out.println("");
            tablePrinter.print();

        }

    }

    public static void exit(){
        System.out.println("");
        System.out.println("A presto!");
    }

}
