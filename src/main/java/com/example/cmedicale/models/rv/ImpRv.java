package com.example.cmedicale.models.rv;

import com.example.cmedicale.entity.creneau;
import com.example.cmedicale.entity.rv;
import com.example.cmedicale.storage.Connect;
import com.example.cmedicale.storage.DbConnect;
import com.example.cmedicale.storage.FileConnect;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpRv implements Irv{
    DbConnect Dbcon = Connect.getDbConnectInstance();
    FileConnect<rv> Filecon = Connect.getFileConnectInstance();
    @Override
    public void ajouterRv(String Type, rv P) {
        if (Type.equals("database")) {
            String req = "INSERT INTO rv (id, jour, id_client, id_creneau) VALUES (?,?,?,?) ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, P.getId());
                ps.setDate(2, (Date) P.getJour());
                ps.setInt(3, P.getId_client());
                ps.setInt(4, P.getId_creaneau());
                if (ps.executeUpdate() == 1) {
                    System.out.println("Added Succesfully ");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Type.equals("file")) {
            try {
                Filecon.SaveToFile(P,"rv.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("error Type");
        }

    }

    @Override
    public void modifierRv(String Type, rv P) {
        if (Type.equals("database")) {
            String req = "UPDATE rv SET jour = ? , id_client = ? , id_creneau = ? WHERE id = ? ";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setDate(1, (Date) P.getJour());
                ps.setInt(2, P.getId_client());
                ps.setInt(3, P.getId_creaneau());
                if (ps.executeUpdate() == 1) {
                    System.out.println("Update Succesfully ");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    @Override
    public void supprimerRv(String Type, int indice) {
        if (Type.equals("database")) {
            String req = "DELETE * FROM rv WHERE id = ? ";
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
    public rv getRv(String Type, int indice) {
        rv med = null ;
        if (Type.equals("database")) {
            String req = "SELECT * FROM rv WHERE id=?";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ps.setInt(1, indice);
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    med = new rv(indice, res.getDate("jour"), res.getInt("id_client"), res.getInt("id_creneau"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return med ;

    }

    @Override
    public List<rv> getRVs(String Type) {
        List<rv> med = new ArrayList<rv>() ;
        if (Type.equals("database")) {
            String req = "SELECT * FROM rv";
            try {
                PreparedStatement ps = Dbcon.getCon().prepareStatement(req);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    med.add(new rv(res.getInt("id"), res.getDate("jour"), res.getInt("id_client"), res.getInt("id_creneau")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return med;

    }
}
