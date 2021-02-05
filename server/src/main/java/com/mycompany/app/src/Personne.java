package com.mycompany.app.src;

public class Personne {

    private String nom = "";
    private String prenom = "";


    public Personne(String _nom, String _prenom) {
        this.nom = _nom;
        this.prenom = _prenom;
    }

    public String get_nom() {
        return this.nom;
    }

    public String get_prenom() {
        return this.prenom;
    }

    public void set_nom(String _nom) {
        this.nom = _nom;
    }

    public void set_prenom(String _prenom) {
        this.prenom = _prenom;
    }
}