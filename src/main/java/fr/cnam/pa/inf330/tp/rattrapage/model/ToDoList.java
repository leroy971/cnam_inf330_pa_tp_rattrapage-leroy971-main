package fr.cnam.pa.inf330.tp.rattrapage.model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<TacheI> taches = new ArrayList<>();
    String fileName;
    String nom;

    public ToDoList(final String nom, final String fileName) {
        this.nom = nom;
        this.fileName = fileName;
    }

    public List<TacheI> getTaches() {
        return taches;
    }

    public void setTaches(final List<TacheI> taches) {
        this.taches = taches;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
}
