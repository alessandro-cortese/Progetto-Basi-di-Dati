package it.uniroma2.dicii.bd.model.domain;

public class Offerta {

    private String utente;
    private String oggetto;
    private Float importo;
    private Float valoreMassimoImporto;

    public Offerta(){}

    public Offerta(String utente, String oggetto, Float importo, Float valoreMassimoImporto){

        this.utente = utente;
        this.oggetto = oggetto;
        this.importo = importo;
        this.valoreMassimoImporto = valoreMassimoImporto;

    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public Float getImporto() {
        return importo;
    }

    public void setImporto(Float importo) {
        this.importo = importo;
    }

    public void setValoreMassimoImporto(Float valoreMassimoImporto) {
        this.valoreMassimoImporto = valoreMassimoImporto;
    }

    public Float getValoreMassimoImporto() {
        return valoreMassimoImporto;
    }
}
