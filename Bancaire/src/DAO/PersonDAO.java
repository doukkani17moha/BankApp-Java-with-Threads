package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Interfaces.IPersonDAO;
import Model.Person;

public class PersonDAO implements IPersonDAO {

    private Connection connection = null;
    private PreparedStatement s1 = null;

    public PersonDAO() {
        // Create a single DBConnection instance to be reused
        DBconnection connec = new DBconnection();
        connection = connec.getConnection();
    }

    @Override
    public void addPerson(Person person) {
         try {
            String query = "INSERT INTO person (firstName, lastName,email, phoneNumber)  VALUES (?, ?, ?,?)";
            s1 = connection.prepareStatement(query);
            s1.setString(1, person.getFirstName());
            s1.setString(2, person.getLastName());
            s1.setString(3, person.getEmail());
            s1.setString(4, person.getPhoneNumber());
            s1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void updatePerson(Person person) {
        try {
			String query = "UPDATE person SET firstName=?, lastName=?, email=?, phoneNumber=? WHERE id=?";
			s1 = connection.prepareStatement(query);
            s1.setString(1, person.getFirstName());
            s1.setString(2, person.getLastName());
            s1.setString(3, person.getEmail());
            s1.setString(4, person.getPhoneNumber());
			s1.setInt(5, person.getId());
			s1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
    }

    @Override
    public void deletePerson(int personId) {
        try {
			String query = "DELETE FROM person WHERE id = ?";
			s1 = connection.prepareStatement(query);
			s1.setInt(1, personId);
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
}
