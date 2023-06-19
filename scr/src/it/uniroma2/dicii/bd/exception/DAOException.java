package it.uniroma2.dicii.bd.exception;

public class DAOException extends RuntimeException{

    public DAOException(){
        super();
    }

    public DAOException(String message, Throwable cause){
        super(message, cause);
    }

    public DAOException(String message){
        super(message);
    }

}
