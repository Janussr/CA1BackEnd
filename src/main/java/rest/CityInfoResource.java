package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CityInfo.CityInfoDTO;
import dtos.CityInfo.CityInfosDTO;
import facades.CityInfoFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("cityinfo")
public class CityInfoResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final CityInfoFacade FACADE = CityInfoFacade.getCityInfoFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //TODO error 500 stackoverflow
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("getall")
    public String getAll() {
        return GSON.toJson(FACADE.getAllCityInfo());
    }

    @GET
    @Produces("application/json")
    @Path("/danish")
    public String getDanishZipCodes() {
        return GSON.toJson(FACADE.getDanishZipCodes());
    }

    @GET
    @Produces("application/json")
    @Path("/getbyid/{citybyid}")
    public String getCityById(@PathParam("citybyid") long cityById) {
        return GSON.toJson(FACADE.getCityById(cityById));
    }

    //TODO error 500
    @GET
    @Produces("application/json")
    @Path(("getbyname"))
    public String getCityByName(String cityName) {
        return GSON.toJson(FACADE.getCityByName(cityName));
    }


    //TODO error 500
    @GET
    @Produces("application/json")
    @Path("/zipcode/{zipcode}")
    public String getCityByZipCode(@PathParam("zipcode") int zipCode) {
        return GSON.toJson(FACADE.getCityByZipCode(zipCode));
    }


}
