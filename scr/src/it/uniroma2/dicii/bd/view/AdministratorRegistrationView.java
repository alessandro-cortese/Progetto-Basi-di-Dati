package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdministratorRegistrationView {

    public static Credentials register() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci un username: ");
        String username = reader.readLine();
        username = username;
        System.out.println("Inserisci una password: ");
        String password = reader.readLine();

        return new Credentials(username, password, Role.AMMINISTRATORI);
    }

}
