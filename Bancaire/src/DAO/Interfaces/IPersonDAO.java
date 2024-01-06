package DAO.Interfaces;

import Model.Person;

public interface IPersonDAO {

     void addPerson(Person person);
     void updatePerson(Person person);
     void deletePerson(int personId); 
} 
