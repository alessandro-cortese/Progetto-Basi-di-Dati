package it.uniroma2.dicii.bd.model.domain;

public class Administrator {

    private String username;
    private String password;

    public Administrator(){

    }

    public Administrator(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
