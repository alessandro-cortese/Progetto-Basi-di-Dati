package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO implements GenericProcedureDAO<Credentials>{

    @Override
    public Credentials execute(Object... params) throws DAOException{

        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);

        } catch (SQLException sqlException) {
            throw new DAOException("Login error: " + sqlException.getMessage());
        }

        return new Credentials(username, password, Role.fromInt(role));

    }

}
