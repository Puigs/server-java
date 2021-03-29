package com.mycompany.app.src;

import com.mycompany.app.src.Personne;

public class PersonneBuilder {

    private String nom = "";
    private String prenom = "";

    public PersonneBuilder() {
    }

    public PersonneBuilder set_nom(String _nom) {
        this.nom = _nom;
        return this;
    }

    public PersonneBuilder set_prenom(String _prenom) {
        this.prenom = _prenom;
        return this;
    }

    public Personne build() {
        return new Personne(this.nom, this.prenom);
    }
}