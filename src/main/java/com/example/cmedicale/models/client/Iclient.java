package com.example.cmedicale.models.client;

import com.example.cmedicale.entity.Personne;
import com.example.cmedicale.entity.client;

import java.util.List;

public interface Iclient {
    void ajouterClient(String Type ,client P);
    void modifierClient(String Type ,client p);
    void supprimerMedecin(String Type , int indice);
    Personne getClient(String Type ,int indice);
    List<client> getClients(String Type);
}
