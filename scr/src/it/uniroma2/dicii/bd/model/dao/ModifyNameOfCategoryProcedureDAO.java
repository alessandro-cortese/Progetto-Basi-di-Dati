package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ModifyNameOfCategoryProcedureDAO implements GenericProcedureDAO <Boolean>{

    private static ModifyNameOfCategoryProcedureDAO instance = null;

    private ModifyNameOfCategoryProcedureDAO(){}

    public static ModifyNameOfCategoryProcedureDAO getInstance(){
        if(instance == null){
            instance = new ModifyNameOfCategoryProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException {

        List<Object> list = (List<Object>) params[0];
        Categoria categoria = (Categoria) list.get(0);
        String nuovoNome = (String) list.get(1);

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call modifica_nome_categoria(?,?)}");
            callableStatement.setString(1, categoria.getNome());
            callableStatement.setString(2, nuovoNome);
            callableStatement.execute();

        }catch (SQLException sqlException){
            throw new DAOException("Errore nella modifica del nome della categoria: " + sqlException.getMessage());
        }

        return true;
    }

}
