package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.LoginProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller{

    Credentials cred = null;

    @Override
    public void start(){

        try {
            cred = LoginView.authenticate();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        try {
            LoginProcedureDAO login = LoginProcedureDAO.getInstance();
            cred = login.execute(cred.getUsername(), cred.getPassword());
        } catch (DAOException e){
            throw new RuntimeException(e);
        }

    }

    public Credentials getCred(){
        return cred;
    }

}
