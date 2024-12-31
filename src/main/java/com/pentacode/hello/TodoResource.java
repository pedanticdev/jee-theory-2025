package com.pentacode.hello;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    PersistenceService persistenceService;

    @POST
    public Response saveTodo(Todo todo) {
        return Response.ok().entity(persistenceService.save(todo)).build();
    }

    @GET
    public List<Todo> loadAllTodos() {
        return persistenceService.findAll();
    }
}
