package com.example.cmedicale.models.creneau;
import com.example.cmedicale.entity.creneau;
import com.example.cmedicale.entity.medecin;

import java.util.List;

public interface Icreneau {
    void ajouterCreneau(String Type ,creneau C);
    void modifierCreneau(String Type ,creneau C);
    void supprimerCreneau(String Type ,int indice);
    creneau getCreneau(String Type ,int indice);
    List<creneau> getCreneaux(String Type);
}
