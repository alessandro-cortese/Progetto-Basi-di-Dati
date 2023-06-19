package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.domain.Role;

import java.sql.SQLException;

public class AmministratoriController implements Controller{

    @Override
    public void start(){

        try{
            ConnectionFactory.changeRole(Role.AMMINISTRATORI);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        System.out.println("Amministratori ok");

    }
}
