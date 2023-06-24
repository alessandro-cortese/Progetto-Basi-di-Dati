package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO implements GenericProcedureDAO<Categoria>{

    private static CategoriaDAO instance;

    private CategoriaDAO(){

    }

    public static CategoriaDAO getInstance(){
        if(instance == null){
            instance = new CategoriaDAO();
        }

        return instance;
    }

    @Override
    public Categoria execute(Object... params) throws DAOException{
        Categoria categoria = null;
        String nomeCategoria = (String) params[0];

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call riprendi_categoria_dal_nome(?)}");
            callableStatement.setString(1, nomeCategoria);
            boolean flag = callableStatement.execute();
            if(flag){

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()){

                    categoria = new Categoria();
                    categoria.setNome(resultSet.getString(1));
                    categoria.setMacrocategoria(resultSet.getString(2));

                }

            }

        } catch (SQLException sqlException) {
            throw new DAOException(sqlException.getMessage());
        }

        return categoria;
    }

}
