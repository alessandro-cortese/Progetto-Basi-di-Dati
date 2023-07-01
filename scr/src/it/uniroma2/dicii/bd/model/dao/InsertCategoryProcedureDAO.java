package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertCategoryProcedureDAO implements GenericProcedureDAO<Boolean>{

    private static InsertCategoryProcedureDAO instance = null;

    private InsertCategoryProcedureDAO(){}

    public static InsertCategoryProcedureDAO getInstance(){
        if(instance == null){
            instance = new InsertCategoryProcedureDAO();
        }

        return instance;
    }

    @Override
    public Boolean execute(Object... params) throws DAOException{

        Boolean flag;
        Categoria categoria = (Categoria) params[0];

        if(categoria.getMacrocategoria() == null){

            flag = insertCategoryWithoutMacroCategory(categoria);

        }else{

            flag = insertCategoryWithMacroCategory(categoria);

        }

        return flag;
    }

    private boolean insertCategoryWithoutMacroCategory(Categoria categoria) throws DAOException{

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call inserisci_categoria_senza_macrocategoria(?)}");
            callableStatement.setString(1,categoria.getNome());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento categoria senza macrocategoria: " + sqlException.getMessage());
        }

        return true;
    }

    private boolean insertCategoryWithMacroCategory(Categoria categoria) throws DAOException{

        try{

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call inserisci_categoria_con_macrocategoria(?,?)}");
            callableStatement.setString(1, categoria.getNome());
            callableStatement.setString(2, categoria.getMacrocategoria());
            callableStatement.execute();

        } catch (SQLException sqlException) {
            throw new DAOException("Errore inserimento categoria con macrocategoria: " + sqlException.getMessage());
        }

        return true;
    }

}
