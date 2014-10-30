package com.example.resources;

import com.example.core.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Path("/user")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @GET
    public Response getAll(){

        List<User> users = new LinkedList<>();
        users.add(
            new User()
                .setUsername("user1")
                .setDisplayName("User 1")
                .setDisplayRole("Admin")
        );
        users.add(
            new User()
                .setUsername("user2")
                .setDisplayName("User 2")
                .setDisplayRole("DBA")
        );

        return Response.ok().entity(users).build();
    }

    @GET
    @Path("/{username}")
    public Response get(@PathParam("username") String username){
        return Response.ok().entity(
                new User()
                    .setUsername(username)
                    .setDisplayName(username)
                    .setDisplayRole("DBA"))
                .build();
    }

    @POST
    public Response add(@Valid User user) {
        return Response.created(URI.create("/user/1234")).entity(user).build();
    }

    @PUT
    @Path("/{username}")
    public Response update(@PathParam("username") String username, @Valid User user) {
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam("username") String username) {
        return Response.noContent().build();
    }
}
