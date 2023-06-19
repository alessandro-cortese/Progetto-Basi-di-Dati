package it.uniroma2.dicii.bd.model.dao;

import it.uniroma2.dicii.bd.exception.DAOException;

import java.sql.SQLException;
import java.util.Objects;

public interface GenericProcedureDAO <P>{

   P execute(Object... params) throws DAOException, SQLException;

}
