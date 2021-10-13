package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Hobby.HobbiesDTO;
import dtos.Hobby.HobbyDTO;
import facades.FacadeExample;
import facades.HobbyFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hobby")
public class HobbyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final HobbyFacade FACADE =  HobbyFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Path("/hobbies")
    @Produces("application/json")
    public String getAll() {
        return GSON.toJson(FACADE.getAllHobbies());

    }


    


}