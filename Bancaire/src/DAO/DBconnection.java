package DAO;
import java.sql.*;
public class DBconnection {
    private Connection con;
    private Statement st;
    private static final String url = "jdbc:mysql://localhost/bancaire";
    private static final String user = "root";
    private static final String password = "";
    //Constructor
    public DBconnection(){
        try {
            // Chargement de pilote
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Ouverture de Connexion
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Statement getStatement() {
         st = null;
        try {
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
    public Connection getConnection() {
        return con;
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

