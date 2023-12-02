package com.example.cmedicale.models.medecin;

import com.example.cmedicale.storage.Connect;
import com.example.cmedicale.entity.medecin;
import com.example.cmedicale.storage.DbConnect;
import com.example.cmedicale.storage.FileConnect;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpMedecin implements Imedecin {
    DbConnect Dbcon = Connect.getDbConnectInstance();
    FileConnect<medecin> Filecon = Connect.getFileConnectInstance();
    @Override
    public void ajouterMedecin(String Type , medecin P) {
        if (Type.equals("database")) {
            String req = "INSERT INTO personne (version,titre,nom,prenom) VALUES (?,?,?,?) ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, P.getVersion());
                ps.setString(2, P.getTitre());
                ps.setString(3, P.getNom());
                ps.setString(4, P.getPrenom());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (Type.equals("file")) {
            try {
                Filecon.SaveToFile(P,"medecin.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("error Type");
        }
    }

    @Override
    public void modifierMedecin(String Type , medecin P) {
        if (Type.equals("database")) {
            String req = "UPDATE personne SET version = ? , nom = ? , prenom = ? WHERE id = ? ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, P.getVersion());
                ps.setString(2, P.getNom());
                ps.setString(3, P.getPrenom());
                ps.setInt(4, P.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }
    @Override
    public void supprimerMedecin(String Type , int indice) {
        if (Type.equals("database")) {
            String req = "DELETE * FROM personne WHERE id = ? ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, indice);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    @Override
    public medecin getMedecin(String Type , int indice) {
        medecin p = null;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE id= ? AND titre= ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, indice);
                ps.setString(2, "medecin");
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    p = new medecin(indice, res.getInt("version"), res.getString("nom"), res.getString("prenom"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return p;
    }

    @Override
    public List<medecin> getMedecins(String Type) {
        List<medecin> med = new ArrayList<medecin>() ;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE titre = ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setString(1, "medecin");
                ResultSet res = ps.executeQuery();
                while (res.next()){
                    med.add( new medecin(res.getInt("id"), res.getInt("version"), res.getString("nom"), res.getString("prenom")));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
        return med ;
    }
}
