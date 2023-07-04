package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaOggettiDAO implements GenericProcedureDAO<ListaOggetti>{

    private static ListaOggettiDAO instance = null;

    private ListaOggettiDAO(){}

    public static ListaOggettiDAO getInstance(){
        if(instance == null){
            instance = new ListaOggettiDAO();
        }

        return instance;
    }

    @Override
    public ListaOggetti execute(Object... params) throws DAOException{

        ListaOggetti listaOggetti = new ListaOggetti();

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call vedi_aste_aperte()}");
            boolean flag = callableStatement.execute();

            if(flag){

                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()){
                    OggettoInAsta oggettoInAsta = new OggettoInAsta();
                    oggettoInAsta.setCodice(resultSet.getString(1));
                    oggettoInAsta.setDescrizione(resultSet.getString(2));
                    oggettoInAsta.setStato(resultSet.getString(3));
                    oggettoInAsta.setDescrizioneDimensioni(resultSet.getString(4));
                    oggettoInAsta.setPrezzoDiBase(resultSet.getFloat(5));
                    oggettoInAsta.setNumeroOfferte(resultSet.getInt(6));
                    oggettoInAsta.setDataFineAsta(resultSet.getDate(7));
                    oggettoInAsta.setOrarioInizioAsta(resultSet.getTime(8));
                    oggettoInAsta.setValoreMassimaOfferta(resultSet.getFloat(9));
                    oggettoInAsta.setCategoria(resultSet.getString(10));
                    listaOggetti.addOggettoInAsta(oggettoInAsta);
                }


            }

        } catch (SQLException sqlException) {
            throw new DAOException("Errore lista oggetti: " + sqlException.getMessage());
        }


        return listaOggetti;
    }

}
