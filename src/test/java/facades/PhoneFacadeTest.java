package facades;

import entities.CityInfo;
import entities.Phone;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PhoneFacadeTest {
    private static EntityManagerFactory emf;
    private static PhoneFacade facade;
    private static Phone phone1, phone2, phone3;


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PhoneFacade.getPhoneFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    void setUp() {

        phone1 = new Phone("12345678", "Iphone");
        phone2 = new Phone("11223344", "Android");
        phone3 = new Phone("55667788", "Huewai");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();

            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Test
    public void getAllPhonesTest() {
        int expected = 3;
        int actual = facade.getAllPhones().getSize();
        assertEquals(expected,actual);

    }

    @Test
    public void getPhoneByIdTest(){
        String expected = "12345678";
        String actual = facade.getPhoneById(phone1.getId()).getNumber();
        assertEquals(expected,actual);
    }

    @AfterEach
    void tearDown() {
    }
}