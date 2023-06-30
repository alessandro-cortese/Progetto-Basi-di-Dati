package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertObjectProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static InsertObjectProcedureDAO instance = null;

    private InsertObjectProcedureDAO(){}

    public static InsertObjectProcedureDAO getInstance(){
        if(instance == null){
            instance = new InsertObjectProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        OggettoInAsta oggettoInAsta = (OggettoInAsta) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call inserisci_oggetto_in_asta(?,?,?,?,?,?)}");
            callableStatement.setString(1, oggettoInAsta.getDescrizione());
            callableStatement.setString(2, oggettoInAsta.getStato());
            callableStatement.setFloat(3, oggettoInAsta.getPrezzoDiBase());
            callableStatement.setString(4, oggettoInAsta.getDescrizioneDimensioni());
            callableStatement.setInt(5, oggettoInAsta.getDurataAsta());
            callableStatement.setString(6, oggettoInAsta.getCategoria().getNome());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore nell'inserimento dell'oggetto da mettere in asta: " + sqlException.getMessage());
        }


        return true;
    }

}
