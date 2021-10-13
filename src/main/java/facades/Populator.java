/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.Person.PersonDTO;
import dtos.RenameMeDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;


public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    public static void testAdd() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = PersonFacade.getInstance(emf);

        Person p1 = new Person("kaj@mail.dk", "Kaj", "Testesen");
        Phone phone = new Phone("54667283", "This is Kaj's phone");
        p1.addPhone(phone);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        p1.addHobby(hobby);
        Address address = new Address("Lyngby Hovedgade 5", "Lyngby");
        p1.setAddress(address);
        CityInfo cityInfo = new CityInfo(2800,"Kongens Lyngby");
        address.setCityInfo(cityInfo);

        PersonDTO createdPerson = new PersonDTO(p1);

        facade.addPerson(createdPerson);
    }

    public static void main(String[] args) {
        //populate();
        //testAdd();
    }
}
