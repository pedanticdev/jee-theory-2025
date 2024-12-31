package com.pentacode.hello;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Path("hello")
public class HelloWorldResource {

    @Inject
    @ConfigProperty(name = "greeting", defaultValue = "world")
    private String greeting;

    @Inject
    @ConfigProperty(name = "defaultGreeting")
    private String defaultGreeting;

    @Inject
    @ConfigProperty(name = "dev.property", defaultValue = "dev")
    private String myConfigValue;

    @Inject
    @ConfigProperty(name = "jakarta.version")
    private String jakartaVersion;

    @Inject
    @ConfigProperty(name = "application.server")
    private String applicationServer;

    @GET
    public Response hello(@QueryParam("name")  String name) {
        System.out.println(myConfigValue);
        String greet;
        if ((name == null) || name.trim().isEmpty()) {
            greet = defaultGreeting;
        } else {
            greet = greeting + " " + name;
        }


        return Response
                .ok(greet)
                .build();
    }
    @GET
    @Path("greet/{name}")
    public JsonObject greet(@PathParam("name") String name) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss Z");
        name = (name != null && !"null".equalsIgnoreCase(name)) ? name : "Anon";

        return Json.createObjectBuilder()
                .add("greeting", "Hello there " + name.toUpperCase(Locale.ENGLISH))
                .add("message", "Getting started with Jakarta EE!")
                .add("platform", "Jakarta EE")
                .add("platformVersion", jakartaVersion)
                .add("implementation", applicationServer)
                .add("date", formatter.format(ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)))
                .build();
    }
    public Response fallbackMethod(@QueryParam("name") String name) {
        // Fallback logic when the hello method fails or exceeds retries
        return Response
                .ok("Fallback data")
                .build();
    }

}