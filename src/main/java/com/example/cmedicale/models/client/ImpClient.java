package com.example.cmedicale.models.client;
import com.example.cmedicale.entity.Personne;
import com.example.cmedicale.entity.client;
import com.example.cmedicale.entity.medecin;
import com.example.cmedicale.storage.Connect;
import com.example.cmedicale.storage.DbConnect;
import com.example.cmedicale.storage.FileConnect;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpClient implements Iclient{
    DbConnect Dbcon = Connect.getDbConnectInstance();
    FileConnect<client> Filecon = Connect.getFileConnectInstance();
    @Override
    public void ajouterClient(String Type ,client P) {
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
                Filecon.SaveToFile(P,"client.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("error Type");
        }
    }
    @Override
    public void modifierClient(String Type ,client P) {
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
    public void supprimerClient(String Type, int indice) {
        if (Type.equals("database")) {
            String req = "DELETE FROM personne WHERE id = ? ";
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
    public client getClient(String Type ,int indice) {
        client p = null;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE id= ? AND titre= ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, indice);
                ps.setString(2, "client");
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    p = new client(indice, res.getInt("version"), res.getString("nom"), res.getString("prenom"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return p;
    }
    @Override
    public client getClientByName(String Type, String nom, String prenom) {
        client p = null;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE nom= ? AND titre= ? and prenom= ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setString(1, nom);
                ps.setString(2, "client");
                ps.setString(3, prenom);
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    p = new client(res.getInt("id"), res.getInt("version"), res.getString("nom"), res.getString("prenom"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return p;
    }
    @Override
    public client getClient(String Type, String Nom, String Prenom) {
        client p = null;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE nom= ? AND titre= ? AND prenom= ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setString(1, Nom);
                ps.setString(2, "medecin");
                ps.setString(2, Prenom);
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    p = new client(res.getInt("id"), res.getInt("version"), res.getString("nom"), res.getString("prenom"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return p;
    }
    @Override
    public List<client> getClients(String Type) {
        List<client> med = new ArrayList<client>() ;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE titre = ?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setString(1, "client");
                ResultSet res = ps.executeQuery();
                while (res.next()){
                    med.add( new client(res.getInt("id"), res.getInt("version"), res.getString("nom"), res.getString("prenom")));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return med ;
    }
}
