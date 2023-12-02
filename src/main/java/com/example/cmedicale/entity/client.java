package com.example.cmedicale.entity;

public class client extends Personne {
    public client( int id,int version, String nom, String prenom) {
        super(id,version, "client", nom, prenom);
    }

    public client( int version, String nom, String prenom) {
        super(version, "client", nom, prenom);
    }
}
