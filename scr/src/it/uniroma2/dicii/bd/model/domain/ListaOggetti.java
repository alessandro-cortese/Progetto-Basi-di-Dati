package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaOggetti {
    List<OggettoInAsta> list = new ArrayList<>();

    public int getSize(){
        return list.size();
    }

    public List<OggettoInAsta> getList(){
        return this.list;
    }

    public void addOggettoInAsta(OggettoInAsta oggettoInAsta){
        this.list.add(oggettoInAsta);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(OggettoInAsta oggettoInAsta : list){
            stringBuilder.append(oggettoInAsta);
        }
        return stringBuilder.toString();
    }

}
