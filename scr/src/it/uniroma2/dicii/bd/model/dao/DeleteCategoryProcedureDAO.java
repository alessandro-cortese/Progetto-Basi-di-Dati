package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCategoryProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static DeleteCategoryProcedureDAO instance = null;

    private DeleteCategoryProcedureDAO(){}

    public static DeleteCategoryProcedureDAO getInstance(){
        if(instance == null){
            instance = new DeleteCategoryProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Categoria categoria = (Categoria) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call elimina_categoria(?)}");
            callableStatement.setString(1, categoria.getNome());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore nella eliminazione della categoria: " + sqlException.getMessage());
        }

        return true;
    }
}
