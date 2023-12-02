package com.example.cmedicale.models.creneau;

import com.example.cmedicale.entity.client;
import com.example.cmedicale.entity.creneau;
import com.example.cmedicale.storage.Connect;
import com.example.cmedicale.storage.DbConnect;
import com.example.cmedicale.storage.FileConnect;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpCreneau implements Icreneau{
    DbConnect Dbcon = Connect.getDbConnectInstance();
    FileConnect<creneau> Filecon = Connect.getFileConnectInstance();
    @Override
    public void ajouterCreneau(String Type ,creneau P) {
        if (Type.equals("database")) {
            String req = "INSERT INTO creneaux (id, version, Hdebut, Hfin, Mdebut, Mfin, id_medecin ) VALUES (?,?,?,?,?,?,?) ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, P.getId());
                ps.setInt(2, P.getVersion());
                ps.setInt(3, P.getHdebut());
                ps.setInt(4, P.getMdebut());
                ps.setInt(5, P.getHfin());
                ps.setInt(6, P.getMfin());
                ps.setInt(7, P.getId_medecin());
                if (ps.executeUpdate() == 1) {
                    System.out.println("Added Succesfully ");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Type.equals("file")) {
            try {
                Filecon.SaveToFile(P,"creneau.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("error Type");
        }
    }

    @Override
    public void modifierCreneau(String Type, creneau P) {
        if (Type.equals("database")) {
            String req = "UPDATE creneaux SET  version=?, Hdebut=? , Hfin=? , Mdebut=?, Mfin=?, id_medecin=? WHERE id = ? ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, P.getVersion());
                ps.setInt(2, P.getHdebut());
                ps.setInt(3, P.getHfin());
                ps.setInt(4, P.getMdebut());
                ps.setInt(5, P.getMfin());
                ps.setInt(6, P.getId_medecin());
                ps.setInt(7, P.getId());
                if (ps.executeUpdate() == 1) {
                    System.out.println("Update Succesfully ");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void supprimerCreneau(String Type, int indice) {
        if (Type.equals("database")) {
            String req = "DELETE * FROM creneaux WHERE id = ? ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, indice);
                if (ps.executeUpdate() == 1) {
                    System.out.println("Delete Succesfully ");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public creneau getCreneau(String Type, int indice) {
        creneau med = null ;
        String req = "SELECT * FROM creneaux WHERE id=?";
        try {
            PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
            ps.setInt(1,indice);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                med = new creneau(res.getInt("id"),res.getInt("version") , res.getInt("Hdebut") ,res.getInt("Hfin")  , res.getInt("Mdebut") ,res.getInt("Mfin") , res.getInt("id_medecin") );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med ;

    }

    @Override
    public List<creneau> getCreneaux(String Type) {
        List<creneau> med = new ArrayList<creneau>() ;
        String req = "SELECT * FROM creneaux";
        try {
            PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                med.add(new creneau(res.getInt("id"),res.getInt("version") , res.getInt("Hdebut") ,res.getInt("Hfin")  , res.getInt("Mdebut") ,res.getInt("Mfin") , res.getInt("id_medecin")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med ;
}


}
