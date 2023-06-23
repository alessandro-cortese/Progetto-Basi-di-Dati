package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.dao.UtenteHomeScreenDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.UtenteHomeScreenView;

import java.io.IOException;
import java.sql.SQLException;

public class UtenteController implements ControllerSession{

    private User user;

    @Override
    public void start(Credentials credentials) {
        try{

            ConnectionFactory.changeRole(Role.UTENTE);
            UtenteHomeScreenDAO utenteHomeScreenDAO = UtenteHomeScreenDAO.getInstance();
            user = utenteHomeScreenDAO.execute(credentials.getUsername());

        }catch (SQLException |DAOException e){
            throw new RuntimeException(e);
        }

        userChooseOperation();

    }

    private void userChooseOperation(){

        while(true){

            int scelta;

            try {
                scelta = UtenteHomeScreenView.showHomeScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }

    }

}
