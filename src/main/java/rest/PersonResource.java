package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Person.PersonDTO;
import entities.Person;
import facades.FacadeExample;
import utils.EMF_Creator;
import facades.PersonFacade;


import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getPersonById(id));
    }

    @Path("/hobby/{hobby}")
    @GET
    @Produces("application/json")
    public String getByHobby(@PathParam("hobby") String hobby) {
        return GSON.toJson(FACADE.getPersonsByHobby(hobby));
    }

    @Path("/number/{number}")
    @GET
    @Produces("application/json")
    public String getByNumber(@PathParam("number") String number) {
        return GSON.toJson(FACADE.getPersonByPhoneNumber(number));
    }

    @Path("/city/{zipCode}")
    @GET
    @Produces("application/json")
    public String getByZip(@PathParam("zipCode") int zipCode) {
        return GSON.toJson(FACADE.getPersonsByCity(zipCode));
    }

    @Path("/hobby/count/{hobby}")
    @GET
    @Produces("application/json")
    public String getHobbyCount(@PathParam("hobby") String hobby) {
        return GSON.toJson(FACADE.getNumberOfPeopleByHobby(hobby));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO pNew = FACADE.addPerson(p);
        return GSON.toJson(pNew);
    }


    @Path("/{id}")
    @PUT
    public String editPerson(@PathParam("id") long id, String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        PersonDTO pEdited = FACADE.editPerson(p);
        return GSON.toJson(pEdited);
    }

    @Path("/numberbyhobby/{hobby}")
    @GET
    @Produces("application/json")
    public String getNumberOfPersonsByHobby(@PathParam("hobby") String hobby) {
        long count = FACADE.getNumberOfPeopleByHobby(hobby);
        return "{\"count\":" + count + "}";
    }

    @Path("/test")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("/populate")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String createTables() {
        return FACADE.createTables();
    }

}
