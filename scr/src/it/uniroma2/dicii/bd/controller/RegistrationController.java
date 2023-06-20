package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegisterProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.RegistrationView;

import java.io.IOException;

public class RegistrationController implements Controller{
    private Boolean flag;
    private Credentials cred = null;
    private User user;

    @Override
    public void start(){

        try {
            cred = RegistrationView.register();
            user = RegistrationView.setUserInfo(cred);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            RegisterProcedureDAO registerProcedureDAO = RegisterProcedureDAO.getInstance();
            flag = registerProcedureDAO.execute(user);
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

    public User getUser(){
        return user;
    }

}
