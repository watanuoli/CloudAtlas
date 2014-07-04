package com.gy4team10.rest.webapp;

import com.gy4team10.service.DateTimeService;
import com.gy4team10.service.GreetingService;
import com.gy4team10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by zhou on 14-6-11.
 */
@Path("user")
public class UserResource {
    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @Autowired
    private UserService userService;


    public UserResource() {
        LOGGER.fine("UserResource()");
    }

    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "";
    }
}
