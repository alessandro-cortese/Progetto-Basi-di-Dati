package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.AdministratorRegisterProcedureDAO;
import it.uniroma2.dicii.bd.model.dao.UserRegisterProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Administrator;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.AdministratorRegistrationView;

import java.io.IOException;

public class AdministratorRegistrationController implements Controller{

    private Boolean flag;
    private Credentials cred = null;
    private Administrator administrator;

    @Override
    public void start(){

        try {
            cred = AdministratorRegistrationView.register();
            administrator = new Administrator(cred.getUsername(), cred.getPassword());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            AdministratorRegisterProcedureDAO administratorRegisterProcedureDAO = AdministratorRegisterProcedureDAO.getInstance();
            flag = administratorRegisterProcedureDAO.execute(administrator);

        }catch (DAOException e){
            throw new RuntimeException(e);
        }

        if(flag){
            System.out.println("Registrazione avvenuta con successo!");
        }

    }

    public Credentials getCred(){
        return cred;
    }

    public Administrator getUser(){
        return administrator;
    }

}
