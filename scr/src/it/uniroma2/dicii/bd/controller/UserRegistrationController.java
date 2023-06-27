package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.UserRegisterProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.UserRegistrationView;

import java.io.IOException;

public class UserRegistrationController implements Controller{
    private Boolean flag;
    private Credentials cred = null;
    private User user;

    @Override
    public void start(){

        try {
            cred = UserRegistrationView.register();
            user = UserRegistrationView.setUserInfo(cred);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            UserRegisterProcedureDAO registerProcedureDAO = UserRegisterProcedureDAO.getInstance();
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
