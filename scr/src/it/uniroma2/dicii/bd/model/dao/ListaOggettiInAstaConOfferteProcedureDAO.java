package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
import it.uniroma2.dicii.bd.model.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaOggettiInAstaConOfferteProcedureDAO implements GenericProcedureDAO<ListaOggetti>{

    private static ListaOggettiInAstaConOfferteProcedureDAO instance = null;

    private ListaOggettiInAstaConOfferteProcedureDAO(){}

    public static ListaOggettiInAstaConOfferteProcedureDAO getInstance(){
        if(instance == null){
            instance = new ListaOggettiInAstaConOfferteProcedureDAO();
        }

        return instance;
    }

    @Override
    public ListaOggetti execute(Object... params) throws DAOException{
        ListaOggetti listaOggetti = new ListaOggetti();
        User user = (User) params[0];
        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call visualizza_stato_aste_con_offerte(?)}");
            callableStatement.setString(1, user.getCodiceFiscale());
            boolean flag = callableStatement.execute();
            if(flag){

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()){

                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setCodice(resultSet.getString(1));
                    oggettoInAsta.setDescrizione(resultSet.getString(2));
                    oggettoInAsta.setStato(resultSet.getString(3));
                    oggettoInAsta.setDescrizioneDimensioni(resultSet.getString(4));
                    oggettoInAsta.setPrezzoDiBase(resultSet.getFloat(5));
                    oggettoInAsta.setDataFineAsta(resultSet.getDate(6));
                    oggettoInAsta.setOrarioInizioAsta(resultSet.getTime(7));
                    oggettoInAsta.setValoreMassimaOfferta(resultSet.getFloat(8));

                    listaOggetti.addOggettoInAsta(oggettoInAsta);

                }

            }

        } catch (SQLException sqlException) {
            throw new DAOException("Errore lista oggetti in asta con offerte: " + sqlException.getMessage());
        }

        return listaOggetti;

    }

}
