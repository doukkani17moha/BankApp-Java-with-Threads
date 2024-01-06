package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Model.Person;
import Model.PersonAccount;

public class PersonAccountDAO {

    private Connection connection = null;
    private PreparedStatement s1 = null;

    public PersonAccountDAO() {
        DBconnection connec = new DBconnection();
        connection = connec.getConnection();
    }

    
    public List<PersonAccount> getAllPersonAccountInfo() {
        List<PersonAccount> personAccountList = new ArrayList<>();
        try {
            String query = "SELECT * FROM personaccountview";
            s1 = connection.prepareStatement(query);
            ResultSet res = s1.executeQuery();
            while (res.next()) {
                int personId = res.getInt("person_id"); 
                String firstName = res.getString("firstName");
                String lastName = res.getString("lastName");
                String email = res.getString("email");
                String phone = res.getString("phoneNumber");
                String accountNumber = res.getString("accountNumber");
                String internalAccountNumber = res.getString("InternalAccountNumber");
                String balance = res.getString("balance");
                Person person = new Person(personId, firstName, lastName, email, phone);
                Account account = new Account(accountNumber,internalAccountNumber, balance );
                PersonAccount personAccount = new PersonAccount(person, account);
                personAccountList.add(personAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return personAccountList;
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
