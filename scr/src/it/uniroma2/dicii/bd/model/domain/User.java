package it.uniroma2.dicii.bd.model.domain;

import java.sql.Date;

public class User {

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String cittaDiNascita;
    private String numeroCartaDiCredito;
    private Date dataDiScadenzaCartaDiCredito;
    private String cvv;
    private String viaDiConsegna;
    private String numeroCivicoDiConsegna;
    private String comuneDiConsegna;
    private String capDiConsegna;
    private String username;
    private String password;

    public User(String codiceFiscale, String nome, String cognome,
                Date dataDiNascita, String cittaDiNascita, String numeroCartaDiCredito,
                Date dataDiScadenzaCartaDiCredito, String cvv, String viaDiConsegna,
                String comuneDiConsegna, String numeroCivicoDiConsegna,
                String capDiConsegna, String username, String password){

        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.cittaDiNascita = cittaDiNascita;
        this.numeroCartaDiCredito = numeroCartaDiCredito;
        this.dataDiScadenzaCartaDiCredito = dataDiScadenzaCartaDiCredito;
        this.cvv = cvv;
        this.viaDiConsegna = viaDiConsegna;
        this.comuneDiConsegna = comuneDiConsegna;
        this.numeroCivicoDiConsegna = numeroCivicoDiConsegna;
        this.capDiConsegna = capDiConsegna;
        this.username = username;
        this.password = password;

    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCittaDiNascita() {
        return cittaDiNascita;
    }

    public void setCittaDiNascita(String cittaDiNascita) {
        this.cittaDiNascita = cittaDiNascita;
    }

    public String getNumeroCartaDiCredito() {
        return numeroCartaDiCredito;
    }

    public void setNumeroCartaDiCredito(String numeroCartaDiCredito) {
        this.numeroCartaDiCredito = numeroCartaDiCredito;
    }

    public Date getDataDiScadenzaCartaDiCredito() {
        return dataDiScadenzaCartaDiCredito;
    }

    public void setDataDiScadenzaCartaDiCredito(Date dataDiScadenzaCartaDiCredito) {
        this.dataDiScadenzaCartaDiCredito = dataDiScadenzaCartaDiCredito;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getViaDiConsegna() {
        return viaDiConsegna;
    }

    public void setViaDiConsegna(String viaDiConsegna) {
        this.viaDiConsegna = viaDiConsegna;
    }

    public String getComuneDiConsegna() {
        return comuneDiConsegna;
    }

    public void setComuneDiConsegna(String comuneDiConsegna) {
        this.comuneDiConsegna = comuneDiConsegna;
    }

    public String getCapDiConsegna() {
        return capDiConsegna;
    }

    public void setCapDiConsegna(String capDiConsegna) {
        this.capDiConsegna = capDiConsegna;
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

    public String getNumeroCivicoDiConsegna() {
        return numeroCivicoDiConsegna;
    }

    public void setNumeroCivicoDiConsegna(String numeroCivicoDiConsegna) {
        this.numeroCivicoDiConsegna = numeroCivicoDiConsegna;
    }
}
