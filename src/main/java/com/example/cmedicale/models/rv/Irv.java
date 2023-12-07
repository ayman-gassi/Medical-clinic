package com.example.cmedicale.models.rv;
import com.example.cmedicale.entity.rv;
import java.util.List;
public interface Irv {
    void ajouterRv(String Type ,rv P);
    void modifierRv(String Type ,rv p);
    void supprimerRv(String Type ,int indice);
    rv getRv(String Type ,int indice);
    rv getRvByClient(String Type ,int indice);
    List<rv> getRVs(String Type );
}
