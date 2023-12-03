package com.example.cmedicale.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
    private static DbConnect instance =  null;
    private static Connection con;

    private DbConnect (){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
            Statement statement = con.createStatement();
            String PersonneQuery = "CREATE TABLE IF NOT EXISTS personne("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "version INT,"
                    + "titre VARCHAR(50),"
                    + "nom VARCHAR(50),"
                    + "prenom VARCHAR(50)"
                    + ")";
            statement.executeUpdate(PersonneQuery);

            String CreneauxQuery = "CREATE TABLE IF NOT EXISTS creneaux("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "version VARCHAR(50),"
                    + "Hdebut INT,"
                    + "Hfin INT,"
                    + "Mdebut INT,"
                    + "Mfin INT,"
                    + "id_medecin INT,"
                    + "FOREIGN KEY (id_medecin) REFERENCES personne(id) ON DELETE CASCADE"
                    + ")";
            statement.executeUpdate(CreneauxQuery);

            String RvQuery = "CREATE TABLE IF NOT EXISTS rv("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "jour DATE,"
                    + "id_client INT,"
                    + "id_creneau INT,"
                    + "FOREIGN KEY (id_client) REFERENCES personne(id) ON DELETE CASCADE,"
                    + "FOREIGN KEY (id_creneau) REFERENCES creneaux(id) ON DELETE CASCADE"
                    + ")";
            statement.executeUpdate(RvQuery);

            System.out.println("Connection Done");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static DbConnect GetInstance(){
        if (instance == null) {
            return new DbConnect();
        }
        return instance;
    }

    public static Connection getCon() {
        return con;
    }
}
