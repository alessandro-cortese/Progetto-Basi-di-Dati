package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteHomeScreenDAO implements GenericProcedureDAO <User>{

    private static UtenteHomeScreenDAO instance = null;

    private UtenteHomeScreenDAO(){}

    public static UtenteHomeScreenDAO getInstance(){
        if(instance == null){
            instance = new UtenteHomeScreenDAO();
        }
        return instance;
    }

    @Override
    public User execute(Object... params) throws DAOException, SQLException{

        String username = (String) params[0];
        User user = null;

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call ottieni_utente_by_username(?)}");
            callableStatement.setString(1, username);
            if(callableStatement.execute()){
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()){

                    user = new User();
                    user.setCodiceFiscale(resultSet.getString(1));
                    user.setNome(resultSet.getString(2));
                    user.setCognome(resultSet.getString(3));
                    user.setDataDiNascita(resultSet.getDate(4));
                    user.setCittaDiNascita(resultSet.getString(5));
                    user.setNumeroCartaDiCredito(resultSet.getString(6));
                    user.setDataDiScadenzaCartaDiCredito(resultSet.getDate(7));
                    user.setCvv(resultSet.getString(8));
                    user.setViaDiConsegna(resultSet.getString(9));
                    user.setComuneDiConsegna(resultSet.getString(10));
                    user.setCapDiConsegna(resultSet.getString(11));
                    user.setUsername(resultSet.getString(12));
                    user.setPassword(resultSet.getString(13));
                    user.setNumeroCivicoDiConsegna(resultSet.getString(14));

                }
            }

        }catch(SQLException e){

            throw new DAOException("Errore obtaining user: " + e.getMessage());

        }

        return user;

    }

}
