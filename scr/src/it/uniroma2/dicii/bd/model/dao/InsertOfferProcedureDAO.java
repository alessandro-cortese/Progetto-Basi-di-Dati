package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Offerta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertOfferProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static InsertOfferProcedureDAO instance = null;

    private InsertOfferProcedureDAO(){}

    public static InsertOfferProcedureDAO getInstance(){
        if(instance == null){
            instance = new InsertOfferProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Boolean flag;
        Offerta offerta = (Offerta) params[0];

        if(offerta.getValoreMassimoImporto() == null){

            flag = insertNormalOffer(offerta);

        }else{

            flag = insertAutomaticCounteroffer(offerta);

        }

        return flag;
    }

    private Boolean insertNormalOffer(Offerta offerta) throws DAOException{

        try {

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call fai_offerta(?,?,?)}");
            callableStatement.setString(1, offerta.getUtente());
            callableStatement.setString(2, offerta.getOggetto());
            callableStatement.setFloat(3, offerta.getImporto());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento offerta: " + sqlException.getMessage());
        }


        return true;
    }

    private Boolean insertAutomaticCounteroffer(Offerta offerta) throws DAOException{

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call fai_prima_offerta_controfferta_automatica(?,?,?,?)}");
            callableStatement.setString(1, offerta.getUtente());
            callableStatement.setString(2, offerta.getOggetto());
            callableStatement.setFloat(3, offerta.getImporto());
            callableStatement.setFloat(4, offerta.getValoreMassimoImporto());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento offerta per controfferta automatica: " + sqlException.getMessage());
        }

        return true;
    }

}
