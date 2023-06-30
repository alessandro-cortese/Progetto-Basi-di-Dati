package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaCategorie {

    List<Categoria> list = new ArrayList<>();

    public int getSize(){
        return list.size();
    }

    public List<Categoria> getList(){
        return this.list;
    }

    public void addOggettoCategoria(Categoria categoria){
        this.list.add(categoria);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Categoria categoria : list){
            stringBuilder.append(categoria);
        }
        return stringBuilder.toString();
    }

}
