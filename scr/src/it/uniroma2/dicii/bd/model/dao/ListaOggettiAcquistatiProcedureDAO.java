package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.model.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaOggettiAcquistatiProcedureDAO implements GenericProcedureDAO<ListaOggetti>{

    private static ListaOggettiAcquistatiProcedureDAO instance = null;
    private ListaOggettiAcquistatiProcedureDAO(){}

    public static ListaOggettiAcquistatiProcedureDAO getInstance(){
        if(instance == null){
            instance = new ListaOggettiAcquistatiProcedureDAO();
        }

        return instance;
    }

    @Override
    public ListaOggetti execute(Object... params) throws DAOException{
        ListaOggetti listaOggetti = new ListaOggetti();
        User user = (User) params[0];

        try {

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call visualizza_oggetti_aggiudicati(?)}");
            callableStatement.setString(1, user.getCodiceFiscale());
            boolean flag = callableStatement.execute();
            if(flag){

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()){

                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setDescrizione(resultSet.getString(1));
                    oggettoInAsta.setStato(resultSet.getString(2));
                    oggettoInAsta.setDescrizioneDimensioni(resultSet.getString(3));
                    oggettoInAsta.setCategoria(resultSet.getString(4));
                    oggettoInAsta.setPrezzoDiVendita(resultSet.getFloat(5));
                    listaOggetti.addOggettoInAsta(oggettoInAsta);

                }

            }

        } catch (SQLException sqlException) {
            throw new DAOException("Errore in lista degli oggetti acquistati: " + sqlException.getMessage());
        }

        return  listaOggetti;
    }

}
