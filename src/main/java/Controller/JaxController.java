package Controller;

import app.IRemoteBean;
import app.RemoteBean;
import app.RestClient;
import domen.City;
import domen.subDomen.Genocide;
import domen.subDomen.SumPopulation;

import javax.naming.*;
import javax.ws.rs.GET;
//import javax.ws.rs.Path;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;


@Path("/genocide")
public class JaxController extends Application {

    @GET
    public Response test() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080");
        final Context ctx = new InitialContext(jndiProperties);
        IRemoteBean remoteBean = (IRemoteBean) ctx.lookup("ejb:/soa-lab2-second/RemoteBean!app.IRemoteBean");

        return Response.ok().entity("OK").build();
    }

//    @GET
//    @Path("/count/{id1}/{id2}/{id3}")
//    public Response count(@PathParam("id1") Long id1,@PathParam("id2") Long id2,@PathParam("id3") Long id3){
//        return Response.ok().entity(id1.toString() + " " + id2.toString() + " " + id3.toString()).build();
//    }

    @GET
    @Path("/count/{id1}/{id2}/{id3}")
    public Response m1(@PathParam("id1") Long id1,@PathParam("id2") Long id2,@PathParam("id3") Long id3) throws KeyManagementException, NoSuchAlgorithmException, NamingException {
        final Hashtable jndiProperties = new Hashtable();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080");
        final Context ctx = new InitialContext(jndiProperties);
        IRemoteBean remoteBean = (IRemoteBean) ctx.lookup("ejb:/soa-lab2-second/RemoteBean!app.IRemoteBean");

        try {
            SumPopulation sumPopulation = remoteBean.getAllCities(id1, id2, id3);
            return Response.ok().entity(sumPopulation).build();
        } catch (Exception e) {
            RestClient restClient = new RestClient();
            SumPopulation sumPopulation = restClient.getAllCities(id1, id2, id3);
            return Response.ok().entity(sumPopulation).build();
        }
    }

    @GET
    @Path("/move-to-poorest/{id}")
    public Response m2(@PathParam("id") Long id) throws KeyManagementException, NoSuchAlgorithmException, NamingException {
        final Hashtable jndiProperties = new Hashtable();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080");
        final Context ctx = new InitialContext(jndiProperties);
        IRemoteBean remoteBean = (IRemoteBean) ctx.lookup("ejb:/soa-lab2-second/RemoteBean!app.IRemoteBean");

        try {
            City city = remoteBean.doGenocide(id);
            return Response.ok().entity(city).build();
        } catch (Exception e) {
            RestClient restClient = new RestClient();
            City city = restClient.doGenocide(id);
            System.out.println("Производим геноцид catch");
            return Response.ok().entity(city).build();
        }


//        RestClient restClient = new RestClient();
//        City city = restClient.doGenocide(id);
//        Genocide genocide = new Genocide();


    }

}
