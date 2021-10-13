package rest;

import dtos.Person.PersonDTO;
import entities.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class PersonResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Person person1, person2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        CityInfo cityInfo = new CityInfo(3000, "TestCity");

        person1 = new Person("Testmail@mail.dk", "TestFirstName", "TestLastName");
        Phone phone1 = new Phone("12345678", "TestPhone");
        person1.addPhone(phone1);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        person1.addHobby(hobby);
        Address address1 = new Address("Testgade 48", "This is a street");
        address1.setCityInfo(cityInfo);
        person1.setAddress(address1);


        person2 = new Person("Moreexamples@mail.dk", "MoreFirstName", "MoreLastName");
        Phone phone2 = new Phone("87654321", "ChinesePhone");
        person2.addPhone(phone2);
        person2.addHobby(hobby);
        Address address2 = new Address("Nygade 3", "This is an Alley");
        address2.setCityInfo(cityInfo);
        person2.setAddress(address2);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.persist(cityInfo);
            em.persist(hobby);
            em.persist(person1);
            em.persist(person2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server up");
        given().when().get("/person/all").then().statusCode(200);
    }


    @Test
    public void testPersonById() {
        given()
                .contentType("application/json")
                .pathParam("id", person1.getId())
                .when()
                .get("/person/{id}")
                .then()
                .statusCode(200)
                .body("firstName", equalTo(person1.getFirstName()))
                .body("lastName", equalTo(person1.getLastName()));

    }

    @Test
    public void TestGetAll() {
        List<PersonDTO> personsDTOs;

        personsDTOs = given()
                .contentType("application/json")
                .when()
                .get("/person/all")
                .then()
                .extract().body().jsonPath().getList("all", PersonDTO.class);

        PersonDTO p1DTO = new PersonDTO(person1);
        PersonDTO p2DTO = new PersonDTO(person2);
        assertThat(personsDTOs, containsInAnyOrder(p1DTO, p2DTO));

    }

    @Test
    public void addPerson() {
        Person person = new Person("addTest@mail.dk", "AddTestFirstName", "AddTestLastName");
        Phone phone = new Phone("54347892", "TestPhone");
        person.addPhone(phone);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        person.addHobby(hobby);
        Address address = new Address("Testgade 63", "This is a street");
        address.setCityInfo(new CityInfo(3000, "TestCity"));
        person.setAddress(address);

        given()
                .contentType(ContentType.JSON)
                .body(new PersonDTO(person))
                .when()
                .post("person")
                .then()
                .body("email", equalTo(person.getEmail()))
                .body("firstName", equalTo(person.getFirstName()))
                .body("lastName", equalTo(person.getLastName()));

    }

    @Test
    public void updatePerson() {
        PersonDTO person = new PersonDTO(person1);
        person.setFirstName("editedName");

        given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .put("person/" + person.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testCount() throws Exception {
        given()
                .contentType("application/json")
                .pathParam("hobby", "Turisme")
                .get("/person/numberbyhobby/{hobby}").then()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(2));
    }
}
