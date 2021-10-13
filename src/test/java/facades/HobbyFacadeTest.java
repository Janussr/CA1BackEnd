package facades;

import dtos.Hobby.HobbiesDTO;
import dtos.Hobby.HobbyDTO;
import entities.Hobby;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Disabled
public class HobbyFacadeTest {
    private static EntityManagerFactory emf;
    private static HobbyFacade repo;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        repo = HobbyFacade.getInstance(emf);
        EntityManager em = emf.createEntityManager();

        Hobby h1 = new Hobby("svømning","wiki/svømning","Hele kroppen","idræt");
        Hobby h2 = new Hobby("gåture","wiki/gåture","friluft","fritid");
        Hobby h3 = new Hobby("baseball","wiki/baseball","boldspil","idræt");
        try {
            em.getTransaction().begin();
            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @AfterAll
    static void tearDown() {
        EntityManager em = emf.createEntityManager();
        try  {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    @org.junit.jupiter.api.Test
    void getAllHobbies() {

        int actual = repo.getAllHobbies().getAll().size();

        int expected = 2;
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void createHobby() {
        HobbyDTO hobbyDTO = new HobbyDTO(new Hobby("baseball","wiki/baseball","boldspil","idræt"));
        repo.createHobby(hobbyDTO);

        int expected =3;
        int actual = repo.getAllHobbies().getAll().size();
        assertEquals(expected, actual);
    }
}
