package it.uniroma2.dicii.bd.model.domain;

import java.lang.invoke.StringConcatFactory;

public class Credentials {

    private final String username;
    private final String password;
    private final Role role;

    public Credentials(String username, String password, Role role){

        this.username = username;
        this.password = password;
        this.role = role;

    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public Role getRole(){
        return this.role;
    }

}
