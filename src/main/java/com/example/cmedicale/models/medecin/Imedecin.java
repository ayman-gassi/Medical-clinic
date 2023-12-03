package com.example.cmedicale.models.medecin;

import com.example.cmedicale.entity.Personne;
import com.example.cmedicale.entity.client;
import com.example.cmedicale.entity.medecin;

import java.util.List;
public interface Imedecin {
    void ajouterMedecin(String Type , medecin P);
    void modifierMedecin(String Type , medecin p);
    void supprimerMedecin(String Type , int indice);
    medecin getMedecin(String Type,int indice);
    medecin getMedecinByName(String Type , String nom, String prenom);
    List<medecin> getMedecins(String Type);
}
