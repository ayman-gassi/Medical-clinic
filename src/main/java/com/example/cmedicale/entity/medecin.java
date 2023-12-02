package com.example.cmedicale.entity;

public class medecin extends Personne {
    public medecin( int version, String nom, String prenom) {
        super(version, "medecin", nom, prenom);
    }

    public medecin(int id, int version, String nom, String prenom) {
        super(id, version, "medecin", nom, prenom);
    }
}
