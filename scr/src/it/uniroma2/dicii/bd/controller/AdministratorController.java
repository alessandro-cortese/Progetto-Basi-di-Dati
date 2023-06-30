package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.*;
import it.uniroma2.dicii.bd.model.domain.*;
import it.uniroma2.dicii.bd.view.AdministratorHomeView;
import it.uniroma2.dicii.bd.view.ApplicationView;
import it.uniroma2.dicii.bd.view.UtenteHomeScreenView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorController implements Controller{

    @Override
    public void start(){

        try{
            ConnectionFactory.changeRole(Role.AMMINISTRATORI);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        administratorChoseOperation();

    }

    private void administratorChoseOperation(){

        while(true){

            int scelta;

            try {
                scelta = AdministratorHomeView.showHomeScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(scelta){

                case 1 -> vediAste();
                case 2 -> inserisciOggetto();
                case 3 -> vediCategorie();
                case 4 -> modificaNomeCategoria();
                case 8 -> exit();

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

        AdministratorHomeView.vediAsteAperte(listaOggetti);

    }

    private void inserisciOggetto(){

        boolean flag = false;
        OggettoInAsta oggettoInAsta = new OggettoInAsta();
        try {

            oggettoInAsta = AdministratorHomeView.getOggettoInAstaInfo();

        }catch (IOException e){
            ApplicationView.printError(e);
        }

        try{

            InsertObjectProcedureDAO insertObjectProcedureDAO = InsertObjectProcedureDAO.getInstance();
            flag = insertObjectProcedureDAO.execute(oggettoInAsta);

        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        if(flag){
            System.out.println("");
            System.out.println("Inserimento avvenuto con successo!");
        }

    }

    private void vediCategorie(){

        ListaCategorie listaCategorie = null;
        try {
            ListaCategorieDAO listaCategorieDAO = ListaCategorieDAO.getInstance();
            listaCategorie = listaCategorieDAO.execute();
        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        AdministratorHomeView.stampaCategorie(listaCategorie);

    }
    private void modificaNomeCategoria(){

        List<Object> list = new ArrayList<>();
        Boolean flag = true;

        try{

            list = AdministratorHomeView.getCategoriaInfo();

        }catch (IOException e){
            ApplicationView.printError(e);
        }

        try{

            ModifyNamaOfCategoryProcedureDAO modifyNamaOfCategoryProcedureDAO = ModifyNamaOfCategoryProcedureDAO.getInstance();
            flag = modifyNamaOfCategoryProcedureDAO.execute(list);

        }catch (DAOException e){
            ApplicationView.printError(e);
        }

        if(flag){
            System.out.println("Inserimento avvenuto con successo!");
        }

    }

    private void exit(){
        AdministratorHomeView.exit();
        System.exit(0);
    }

}
