package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;

public class ApplicationController implements Controller{

    Credentials cred;

    @Override
    public void start() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if(cred.getRole() == null){
            throw new RuntimeException("Invalid Credentials");
        }

        switch (cred.getRole()){
            case UTENTE -> new UtenteController().start();
            case AMMINISTRATORI -> new AmministratoriController().start();
            default -> throw new RuntimeException("Invalid Credentials");
        }

    }
}
