package app;

import Controller.JaxController;
import Filter.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class CallerApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(CorsFilter.class);
        resources.add(JaxController.class);
//        resources.add(ServerErrorMapper.class);
//        resources.add(BadRequestMapper.class);
        return resources;
    }
}
