
package services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Trieda na konfiguraciu WEB servisu
 * @author Janči
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(services.SensorResource.class);
        resources.add(services.UserResource.class);
        resources.add(services.ValueResource.class);
    }
    
}
