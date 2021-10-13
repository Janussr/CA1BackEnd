package facades;

import entities.CityInfo;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class CityInfoFacadeTest {
    private static EntityManagerFactory emf;
    private static CityInfoFacade facade;
    private static CityInfo cityInfo1, cityInfo2, cityInfo3;


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CityInfoFacade.getCityInfoFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    // Setup
    @BeforeEach
    public void setUp() {

        cityInfo1 = new CityInfo(3500,"valby");
        cityInfo2 = new CityInfo(4500,"haslev");
        cityInfo3 = new CityInfo(2500,"hvidovre");


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

            em.persist(cityInfo1);
            em.persist(cityInfo2);
            em.persist(cityInfo3);

            em.getTransaction().commit();

        } finally {
            em.close();
        }

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    @Test
    public void getAllCityInfoTest() {
        int expected = 3;
        int actual = facade.getAllCityInfo().getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void getDanishZipCodesTest(){
        int expected = 3;
        int actual = facade.getDanishZipCodes().size();
        assertEquals(expected,actual);
    }


    @Test
    public void getCityByIdTest() {
        int expected = 3500;
        int actual = facade.getCityById(cityInfo1.getId()).getZipcode();
        assertEquals(expected, actual);
    }

    @Test
    public void getCityByNameTest() {
        String expected = "valby";
        String actual = facade.getCityByName("valby").getCity();
        assertEquals(expected, actual);

    }
    @Test
    public void getCityByZipcodeTest(){
        int expected = 3500;
        int actual = facade.getCityByZipCode(cityInfo1.getZipCode()).getZipcode();
        assertEquals(expected,actual);
    }


}