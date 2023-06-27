package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Administrator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AdministratorRegisterProcedureDAO implements GenericProcedureDAO<Boolean>{

    public static AdministratorRegisterProcedureDAO instance = null;
    private AdministratorRegisterProcedureDAO(){

    }

    public static AdministratorRegisterProcedureDAO getInstance(){
        if(instance == null){
         instance = new AdministratorRegisterProcedureDAO();
        }
        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Administrator administrator = (Administrator) params[0];

        try {

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call registrazione_amministratori(?,?)}");
            callableStatement.setString(1,administrator.getUsername());
            callableStatement.setString(2, administrator.getPassword());
            callableStatement.executeQuery();

        } catch (SQLException sqlException) {
            throw new DAOException("Error : " + sqlException.getMessage());
        }

        return true;
    }

}
