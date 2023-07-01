package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ModifyMacroCategoryProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static ModifyMacroCategoryProcedureDAO instance = null;

    private ModifyMacroCategoryProcedureDAO(){}

    public static ModifyMacroCategoryProcedureDAO getInstance(){
        if(instance == null){
            instance = new ModifyMacroCategoryProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Categoria categoria = (Categoria) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call modifica_macrocategoria(?,?)}");
            callableStatement.setString(1, categoria.getNome());
            callableStatement.setString(2, categoria.getMacrocategoria());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore nella modifica della macrocategoria: " + sqlException.getMessage());
        }

        return true;
    }

}
