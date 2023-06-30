package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaCategorieDAO implements GenericProcedureDAO<ListaCategorie>{

    private static ListaCategorieDAO instance = null;

    private ListaCategorieDAO(){}

    public static ListaCategorieDAO getInstance(){
        if(instance == null){
            instance = new ListaCategorieDAO();
        }

        return instance;
    }

    @Override
    public ListaCategorie execute(Object... params) throws DAOException{

        ListaCategorie listaCategorie = new ListaCategorie();

        try {

            Connection connection = ConnectionFactory.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call mostra_categorie()}");
            boolean flag = callableStatement.execute();

            if(flag){

                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()){

                    Categoria categoria = new Categoria();
                    categoria.setNome(resultSet.getString(1));
                    categoria.setMacrocategoria(resultSet.getString(2));
                    if(categoria.getMacrocategoria() == null){
                        categoria.setMacrocategoria("<Non ha una macrocategoria>");
                    }
                    listaCategorie.addOggettoCategoria(categoria);

                }

            }

        } catch (SQLException sqlException) {
            throw new DAOException("Errore in lista categorie: " + sqlException.getMessage());
        }

        return listaCategorie;
    }

}
