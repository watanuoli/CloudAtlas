package com.gy4team10.rest.webapp;

/**
 * Created by zhou on 14-6-11.
 */

import java.util.concurrent.atomic.AtomicInteger;

import com.gy4team10.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Jersey2 Spring integration example.
 * Demonstrate how to use Spring managed JAX-RS resource class with singleton scope (+ Spring bean DI).
 *
 * @author Marko Asplund (marko.asplund at gmail.com)
 */
@Path("spring-singleton-hello")
@Component
public class SpringSingletonResource {

    AtomicInteger counter = new AtomicInteger();

    @Autowired
    private GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(@Context HttpHeaders headers, @QueryParam("p1") String p1) {
        if("foobar".equals(p1)) {
            throw new IllegalArgumentException("foobar is illegal");
        }
        return String.format("%d: %s", counter.incrementAndGet(), greetingService.greet("world"));
    }
}
