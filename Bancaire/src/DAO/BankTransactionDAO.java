package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BankTransaction;

public class BankTransactionDAO {

    private Connection connection = null;
    private PreparedStatement s1 = null;

    public BankTransactionDAO() {
        // Create a single DBConnection instance to be reused
        DBconnection connec = new DBconnection();
        connection = connec.getConnection();
    }

    public List<BankTransaction> getAllPersonAccountInfo() {
        List<BankTransaction> transacList = new ArrayList<>();
        try {
            String query = "SELECT * FROM banktransaction";
            s1 = connection.prepareStatement(query);
            ResultSet res = s1.executeQuery();
            while (res.next()) {
                int Id = res.getInt("id"); 
                String amount = res.getString("amount");
                String date = res.getString("date");
                String type = res.getString("TransactionType");
                String src = res.getString("sourceAccountNumber");
                String trg = res.getString("targetAccountNumber");
                BankTransaction bankTransaction = new BankTransaction(Id, amount, date, type, src, trg);
                transacList.add(bankTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return transacList;
    }

     public int logTransaction(String filePath) {
        int status = 0;
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            String query = "SELECT * FROM banktransaction";
            s1 = connection.prepareStatement(query);
            ResultSet resultSet = s1.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String amount = resultSet.getString("amount");
                String date = resultSet.getString("date");
                String TransactionType = resultSet.getString("TransactionType");
                String sourceAccountNumber = resultSet.getString("sourceAccountNumber");
                String targetAccountNumber = resultSet.getString("targetAccountNumber");
                String line = String.format("%d, %s, %s, %s, %s, %s %n",id, amount, date, TransactionType, sourceAccountNumber, targetAccountNumber);
                writer.write(line);
                status = 1;
            }
            System.out.println("Logs Extracted Successfully in : " + filePath);
            writer.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return status;
    }

    private void closeResources() {
        try {
            if (s1 != null) {
                s1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
