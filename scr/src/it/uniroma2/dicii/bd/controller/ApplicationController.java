package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.ApplicationView;

public class ApplicationController implements Controller{

    Credentials cred;
    User user;

    @Override
    public void start() {

        while(true){

            int choise;
            choise = ApplicationView.showApplicationView();
            switch (choise){

                case 1 -> registration();
                case 2 -> login();
                default -> throw new RuntimeException("Scelta non valida!");
            }

        }

    }

    private void registration(){

        RegistrationController registrationController = new RegistrationController();
        registrationController.start();
        user = registrationController.getUser();
        cred = registrationController.getCred();


    }

    private void login(){

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
