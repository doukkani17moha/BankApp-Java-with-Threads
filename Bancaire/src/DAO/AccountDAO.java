package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DAO.Interfaces.IAccountDAO;
import Model.Account;

public class AccountDAO implements IAccountDAO {

    private Connection connection = null;
    private PreparedStatement s1 = null;

    public AccountDAO() {
        // Create a single DBConnection instance to be reused
        DBconnection connec = new DBconnection();
        connection = connec.getConnection();
    }

    @Override
    public void createAccount(Account account) {
        String accountNumber = generateRandomAccountNumber();
        String internalAccountNumber = generateRandomInternalAccountNumber();
        account.setAccountNumber(accountNumber);
        account.setInternalAccountNumber(internalAccountNumber);
        try {
            String query2 = "SELECT id FROM person ORDER BY id DESC LIMIT 1";
            s1 = connection.prepareStatement(query2);
            ResultSet res = s1.executeQuery();
            if(res.next()){
                int idd = res.getInt("id");
                account.setAccountHolder(idd);
            }
            String query = "INSERT INTO account (accountNumber, InternalAccountNumber,accountHolder, balance)  VALUES (?, ?, ?,?)";
            s1 = connection.prepareStatement(query);
            s1.setString(1, account.getAccountNumber());
            s1.setString(2, account.getInternalAccountNumber());
            s1.setInt(3, account.getAccountHolder());
            s1.setString(4, account.getBalance());
            s1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public Account getAccountById(int accountId) {
        Account account = null;
        try {
            String query = "SELECT * FROM account WHERE id = ?";
            s1 = connection.prepareStatement(query);
            s1.setInt(1, accountId);
            ResultSet res = s1.executeQuery();
            if (res.next()) {
                int idd = res.getInt("id");
                String accNum = res.getString("accountNumber");
                String InaccNum = res.getString("InternalAccountNumber");
                int accHol = res.getInt("accountHolder");
                String bal = res.getString("balance");
                account = new Account(idd, accNum, InaccNum, accHol, bal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return account;
    }

    public Account getAccountByAccountNumber(String accountId) {
        Account account = null;
        try {
            String query = "SELECT * FROM account WHERE accountNumber = ?";
            s1 = connection.prepareStatement(query);
            s1.setString(1, accountId);
            ResultSet res = s1.executeQuery();
            if (res.next()) {
                int idd = res.getInt("id");
                String accNum = res.getString("accountNumber");
                String InaccNum = res.getString("InternalAccountNumber");
                int accHol = res.getInt("accountHolder");
                String bal = res.getString("balance");
                account = new Account(idd, accNum, InaccNum, accHol, bal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return account;
    }

    public List<String> getAllAccounts() {
		List<String> personNames = new ArrayList<>();
		try {
			String query = "SELECT accountNumber FROM account";
			s1 = connection.prepareStatement(query);
			ResultSet res = s1.executeQuery();

			while (res.next()) {
				String personName = res.getString("accountNumber");
				personNames.add(personName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
        return personNames;
    }


    @Override
    public void updateAccount(Account account) {
        try {
            String query = "UPDATE account SET balance=? WHERE accountNumber=?";
            s1 = connection.prepareStatement(query);
            s1.setString(1, account.getBalance());
            s1.setString(2, account.getAccountNumber());
            s1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public synchronized void deposit(String accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);
    
        if (account != null) {
            double balance = Double.parseDouble(account.getBalance());
            balance += amount;
            account.setBalance(String.valueOf(balance));
            updateAccount(account);
    
            System.out.println("Deposit of " + amount + " euros to account " + accountNumber + " successful. New balance: " + balance);
        } else {
            System.out.println("Account with account number " + accountNumber + " not found.");
        }
    }

    public synchronized void withdraw(String accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);
    
        if (account != null) {
            double balance = Double.parseDouble(account.getBalance());
    
            if (balance >= amount) {
                balance -= amount;
                account.setBalance(String.valueOf(balance));
                updateAccount(account);
    
                System.out.println("Withdrawal of " + amount + " euros from account " + accountNumber +
                        " successful. New balance: " + balance);
            } else {
                System.out.println("Insufficient funds for withdrawal from account " + accountNumber);
            }
        } else {
            System.out.println("Account with account number " + accountNumber + " not found.");
        }
    }

    public synchronized void transfer(String sourceAccountNumber, String targetAccountNumber, double amount) {
        // Withdraw from the source account
        withdraw(sourceAccountNumber, amount);
    
        // Deposit to the target account
        deposit(targetAccountNumber, amount);
        
        System.out.println("Transfer of " + amount + " euros from account " + sourceAccountNumber +
                " to account " + targetAccountNumber + " successful.");
    }
    
    @Override
    public void deleteAccount(String accountId) {
        try {
            String query = "DELETE FROM account WHERE accountNumber = ?";
            s1 = connection.prepareStatement(query);
            s1.setString(1, accountId);
            s1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
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

    private String generateRandomAccountNumber() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }

    private String generateRandomInternalAccountNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }

}
