package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.dao.ListaOggettiDAO;
import it.uniroma2.dicii.bd.model.dao.UtenteHomeScreenDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.ApplicationView;
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

            switch(scelta){

                case 1 -> vediAste();
                case 5 -> exit();
            }

        }

    }

    private void vediAste(){

        ListaOggetti listaOggetti = null;
        try{
            ListaOggettiDAO listaOggettiDAO = ListaOggettiDAO.getInstance();
            listaOggetti = listaOggettiDAO.execute();
        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        UtenteHomeScreenView.vediAsteAperte(listaOggetti);

    }

    private void exit(){
        UtenteHomeScreenView.exit();
        System.exit(0);
    }

}
