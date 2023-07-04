package it.uniroma2.dicii.bd.model.domain;

import java.sql.Time;
import java.util.Date;

public class OggettoInAsta {

    private String codice;
    private String descrizione;
    private String stato;
    private Float prezzoDiBase;
    private String descrizioneDimensioni;
    private String tipoOggetto;
    private Integer numeroOfferte;
    private Date dataInizioAsta;
    private Date dataFineAsta;
    private Time orarioInizioAsta;
    private String categoria;
    private Float prezzoDiVendita;
    private User proprietario;
    private Integer durataAsta;
    private Float valoreMassimaOfferta;

    public OggettoInAsta(){}

    public OggettoInAsta(String codice, String descrizione, String stato, Float prezzoDiBase,
                         String descrizioneDimensioni, String tipoOggetto, Integer numeroOfferte,
                         Date dataInizioAsta, Date dataFineAsta, Time orarioInizioAsta, String categoria,
                         Float prezzoDiVendita, User proprietario, Integer durataAsta, Float valoreMassimaOfferta){

        this.codice = codice;
        this.descrizione = descrizione;
        this.stato = stato;
        this.prezzoDiBase = prezzoDiBase;
        this.descrizioneDimensioni = descrizioneDimensioni;
        this.tipoOggetto = tipoOggetto;
        this.numeroOfferte = numeroOfferte;
        this.dataInizioAsta = dataInizioAsta;
        this.dataFineAsta = dataFineAsta;
        this.orarioInizioAsta = orarioInizioAsta;
        this.categoria = categoria;
        this.prezzoDiVendita = prezzoDiVendita;
        this.proprietario = proprietario;
        this.durataAsta = durataAsta;
        this.valoreMassimaOfferta = valoreMassimaOfferta;

    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Float getPrezzoDiBase() {
        return prezzoDiBase;
    }

    public void setPrezzoDiBase(Float prezzoDiBase) {
        this.prezzoDiBase = prezzoDiBase;
    }

    public String getDescrizioneDimensioni() {
        return descrizioneDimensioni;
    }

    public void setDescrizioneDimensioni(String descrizioneDimensioni) {
        this.descrizioneDimensioni = descrizioneDimensioni;
    }

    public String getTipoOggetto() {
        return tipoOggetto;
    }

    public void setTipoOggetto(String tipoOggetto) {
        this.tipoOggetto = tipoOggetto;
    }

    public Integer getNumeroOfferte() {
        return numeroOfferte;
    }

    public void setNumeroOfferte(Integer numeroOfferte) {
        this.numeroOfferte = numeroOfferte;
    }

    public Date getDataInizioAsta() {
        return dataInizioAsta;
    }

    public void setDataInizioAsta(Date dataInizioAsta) {
        this.dataInizioAsta = dataInizioAsta;
    }

    public Date getDataFineAsta() {
        return dataFineAsta;
    }

    public void setDataFineAsta(Date dataFineAsta) {
        this.dataFineAsta = dataFineAsta;
    }

    public Time getOrarioInizioAsta() {
        return orarioInizioAsta;
    }

    public void setOrarioInizioAsta(Time orarioInizioAsta) {
        this.orarioInizioAsta = orarioInizioAsta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPrezzoDiVendita() {
        return prezzoDiVendita;
    }

    public void setPrezzoDiVendita(Float prezzoDiVendita) {
        this.prezzoDiVendita = prezzoDiVendita;
    }

    public User getProprietario() {
        return proprietario;
    }

    public void setProprietario(User proprietario) {
        this.proprietario = proprietario;
    }

    public Integer getDurataAsta() {
        return durataAsta;
    }

    public void setDurataAsta(Integer durataAsta) {
        this.durataAsta = durataAsta;
    }

    public Float getValoreMassimaOfferta() {
        return valoreMassimaOfferta;
    }

    public void setValoreMassimaOfferta(Float valoreMassimaOfferta) {
        this.valoreMassimaOfferta = valoreMassimaOfferta;
    }
}
