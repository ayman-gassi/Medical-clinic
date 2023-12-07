package com.example.cmedicale.models.client;

import com.example.cmedicale.entity.Personne;
import com.example.cmedicale.entity.client;

import java.util.List;

public interface Iclient {
    void ajouterClient(String Type ,client P);
    void modifierClient(String Type ,client p);
    void supprimerClient(String Type , int indice);
    client getClient(String Type ,int indice);
    client getClientByName(String Type ,String nom,String prenom);
    client getClient(String Type ,String Nom,String Prenom);

    List<client> getClients(String Type);
}
