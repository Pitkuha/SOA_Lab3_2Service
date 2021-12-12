package Controller;

import app.RestClient;
import domen.City;
import domen.subDomen.Genocide;
import domen.subDomen.SumPopulation;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


@Path("/genocide")
public class JaxController extends Application {

    @GET
    public Response test(){
        return Response.ok().entity("I'M ALIVE").build();
    }

//    @GET
//    @Path("/count/{id1}/{id2}/{id3}")
//    public Response count(@PathParam("id1") Long id1,@PathParam("id2") Long id2,@PathParam("id3") Long id3){
//        return Response.ok().entity(id1.toString() + " " + id2.toString() + " " + id3.toString()).build();
//    }

    @GET
    @Path("/count/{id1}/{id2}/{id3}")
    public Response m1(@PathParam("id1") Long id1,@PathParam("id2") Long id2,@PathParam("id3") Long id3) throws KeyManagementException, NoSuchAlgorithmException {
        RestClient restClient = new RestClient();
        SumPopulation sumPopulation = restClient.getAllCities(id1, id2, id3);
        return Response.ok().entity(sumPopulation).build();
    }

    @GET
    @Path("/move-to-poorest/{id}")
    public Response m2(@PathParam("id") Long id) throws KeyManagementException, NoSuchAlgorithmException {
        RestClient restClient = new RestClient();
        City city = restClient.doGenocide(id);
        Genocide genocide = new Genocide();

        return Response.ok().entity(city).build();
    }

}
