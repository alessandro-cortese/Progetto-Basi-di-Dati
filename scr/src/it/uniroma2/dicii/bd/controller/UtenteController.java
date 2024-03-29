package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.*;
import it.uniroma2.dicii.bd.model.domain.*;
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
                case 2 -> vediAsteConOfferte();
                case 3 -> faiOfferta();
                case 4 -> vediOggettiAcquistati();
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

    private void vediAsteConOfferte(){

        ListaOggettiInAstaConOfferteProcedureDAO listaOggettiInAstaConOfferteProcedureDAO = ListaOggettiInAstaConOfferteProcedureDAO.getInstance();
        ListaOggetti listaOggetti = null;

        try{

            listaOggetti = listaOggettiInAstaConOfferteProcedureDAO.execute(user);

        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        if(listaOggetti != null){
            UtenteHomeScreenView.vediAsteAperteConOfferte(listaOggetti);
        }

    }

    private void faiOfferta(){

        boolean flag = false;
        Offerta offerta = null;
        try {

            offerta = UtenteHomeScreenView.getOffertaInfo(user);

        } catch (IOException e) {
            ApplicationView.printError(e);
        }

        try{

            InsertOfferProcedureDAO insertOfferProcedureDAO = InsertOfferProcedureDAO.getInstance();
            flag = insertOfferProcedureDAO.execute(offerta);

        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        if (flag){
            System.out.println("");
            System.out.println("Inserimento dell'offerta avvenuto con successo!");
        }

    }

    private void vediOggettiAcquistati(){

        ListaOggettiAcquistatiProcedureDAO listaOggettiAcquistatiProcedureDAO = ListaOggettiAcquistatiProcedureDAO.getInstance();
        ListaOggetti listaOggetti = null;

        try{

            listaOggetti = listaOggettiAcquistatiProcedureDAO.execute(user);

        }catch(DAOException e){
            ApplicationView.printError(e);
        }


        if(listaOggetti != null){

            UtenteHomeScreenView.vediOggettiAcquistati(listaOggetti);

        }

    }

    private void exit(){
        UtenteHomeScreenView.exit();
        System.exit(0);
    }

}
