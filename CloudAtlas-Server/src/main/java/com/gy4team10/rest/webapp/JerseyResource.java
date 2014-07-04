package com.gy4team10.rest.webapp;

/**
 * Created by zhou on 14-6-11.
 */
import com.gy4team10.service.DateTimeService;
import com.gy4team10.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Jersey2 Spring integration example.
 * Demonstrate how to inject a Spring bean into a Jersey managed JAX-RS resource class.
 *
 * @author Marko Asplund (marko.asplund at gmail.com)
 */
@Path("jersey-hello")
public class JerseyResource {
    private static final Logger LOGGER = Logger.getLogger(JerseyResource.class.getName());

    @Autowired
    private GreetingService greetingService;

    //    @Autowired
    @Inject
    private DateTimeService timeService;

    public JerseyResource() {
        LOGGER.fine("HelloWorldResource()");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return String.format("%s: %s", timeService.getDateTime(), greetingService.greet("world"));
    }

}
