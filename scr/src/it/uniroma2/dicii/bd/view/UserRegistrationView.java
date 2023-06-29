package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.model.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserRegistrationView {

    public static Credentials register() throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci un username: ");
        String username = reader.readLine();
        System.out.println("Inserisci una password: ");
        String password = reader.readLine();

        return new Credentials(username, password, Role.UTENTE);
    }

    public static User setUserInfo(Credentials credentials) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il codice fiscale: ");
        String codiceFiscale = reader.readLine();
        System.out.println("Inserisci il tuo nome: ");
        String nome = reader.readLine();
        System.out.println("Inserisci il tuo cognome: ");
        String cognome = reader.readLine();

        java.sql.Date dataDiNascita = null;

        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            System.out.println("Inserisci la tua data di nascita: [yyyy-mm-dd]");
            Date date = dateFormat.parse(reader.readLine());
            dataDiNascita = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("Errore nell'inserimento della data di nascita");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Inserisci la tua città di nascita: ");
        String cittaDiNascita = reader.readLine();
        System.out.println("Inserisci il numero della tua carta di credito: ");
        String numeroCartaDiCredito = reader.readLine();

        java.sql.Date dataDiScadenzaCartaDiCredito = null;

        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            System.out.println("Inserisci la data di scadenza della carta di credito: [yyyy-mm-dd]");
            Date date = dateFormat.parse(reader.readLine());
            dataDiScadenzaCartaDiCredito = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("Errore nell'inserimento della data di scadenza della carta di credito: ");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Inserisci il cvv della carta di credito: ");
        String cvv = reader.readLine();
        System.out.println("Inserisci la via dove consegnare i tuoi acquisti: ");
        String vaiDiConsegna = reader.readLine();
        System.out.println("Inserisci il numero civico:");
        String numeroCivicoDiConsegna = reader.readLine();
        System.out.println("Inseirsci il comune dove consegnare i tuoi acquisti: ");
        String comuneConsegna = reader.readLine();
        System.out.println("Inserisci il cap del comune della consegna: ");
        String capDiConsegna = reader.readLine();


        User user = new User(codiceFiscale,
                nome,
                cognome,
                dataDiNascita,
                cittaDiNascita,
                numeroCartaDiCredito,
                dataDiScadenzaCartaDiCredito,
                cvv,
                vaiDiConsegna,
                comuneConsegna,
                numeroCivicoDiConsegna,
                capDiConsegna,
                credentials.getUsername(),
                credentials.getPassword());

        return user;
    }

}
