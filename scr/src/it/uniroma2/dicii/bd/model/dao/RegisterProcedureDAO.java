package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static RegisterProcedureDAO registerDAO = null;
    private RegisterProcedureDAO(){}

    public static RegisterProcedureDAO getInstance(){
        if(registerDAO == null){
            registerDAO = new RegisterProcedureDAO();
        }
        return registerDAO;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{
        User user = (User) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call registrazione(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, user.getCodiceFiscale());
            callableStatement.setString(2, user.getNome());
            callableStatement.setString(3, user.getCognome());
            callableStatement.setDate(4, user.getDataDiNascita());
            callableStatement.setString(5, user.getCittaDiNascita());
            callableStatement.setString(6, user.getNumeroCartaDiCredito());
            callableStatement.setDate(7, user.getDataDiScadenzaCartaDiCredito());
            callableStatement.setString(8 ,user.getCvv());
            callableStatement.setString(9, user.getViaDiConsegna());
            callableStatement.setString(10, user.getNumeroCivicoDiConsegna());
            callableStatement.setString(11, user.getComuneDiConsegna());
            callableStatement.setString(12, user.getCapDiConsegna());
            callableStatement.setString(13, user.getUsername());
            callableStatement.setString(14, user.getPassword());

            callableStatement.executeQuery();

        }catch (SQLException e){
            throw new DAOException("Error: " + e.getMessage());
        }

        return true;
    }

}
