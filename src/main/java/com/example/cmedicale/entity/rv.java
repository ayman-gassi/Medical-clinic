package com.example.cmedicale.entity;

import java.util.Date;

public class rv {
    private int id;
    private Date jour;
    private int id_client;
    private int id_creaneau;

    public rv(int id, Date jour, int id_client, int id_creaneau) {
        this.id = id;
        this.jour = jour;
        this.id_client = id_client;
        this.id_creaneau = id_creaneau;
    }
    public rv( Date jour, int id_client, int id_creaneau) {
        this.jour = jour;
        this.id_client = id_client;
        this.id_creaneau = id_creaneau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_creaneau() {
        return id_creaneau;
    }

    public void setId_creaneau(int id_creaneau) {
        this.id_creaneau = id_creaneau;
    }
}
