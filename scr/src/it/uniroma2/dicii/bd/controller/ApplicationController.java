package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Administrator;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.ApplicationView;
import it.uniroma2.dicii.bd.view.RegistrationView;

public class ApplicationController implements Controller{

    Credentials cred;
    User user;
    Administrator administrator;

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

        int type;
        type = RegistrationView.getTypeOfRegistration();
        if(type == 1){

            UserRegistrationController userRegistrationController = new UserRegistrationController();
            userRegistrationController.start();
            user = userRegistrationController.getUser();
            cred = userRegistrationController.getCred();
            UtenteController utenteController = new UtenteController();
            utenteController.start(cred);

        }else{

            AdministratorRegistrationController administratorRegistrationController = new AdministratorRegistrationController();
            administratorRegistrationController.start();
            administrator = administratorRegistrationController.getUser();
            cred = administratorRegistrationController.getCred();
            AdministratorController administratorController = new AdministratorController();
            administratorController.start();
        }


    }

    private void login(){

        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if(cred.getRole() == null){
            throw new RuntimeException("Invalid Credentials");
        }

        switch (cred.getRole()){
            case UTENTE -> new UtenteController().start(cred);
            case AMMINISTRATORI -> new AdministratorController().start();
            default -> throw new RuntimeException("Invalid Credentials");
        }

    }

}
