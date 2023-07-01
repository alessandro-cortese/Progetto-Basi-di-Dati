package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteMacroCategotyProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static DeleteMacroCategotyProcedureDAO instance = null;

    private DeleteMacroCategotyProcedureDAO(){}

    public static DeleteMacroCategotyProcedureDAO getInstance(){
        if(instance == null){
            instance = new DeleteMacroCategotyProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Categoria categoria = (Categoria) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call elimina_macrocategoria(?)}");
            callableStatement.setString(1, categoria.getNome());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore nell'eliminazione della macrocategoria: " + sqlException.getMessage());
        }

        return true;
    }

}
