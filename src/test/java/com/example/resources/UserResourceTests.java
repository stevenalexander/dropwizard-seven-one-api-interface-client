package com.example.resources;

import com.example.core.User;
import com.example.core.UserTests;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link io.dropwizard.testing.junit.ResourceTestRule}
 */
public class UserResourceTests {

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UserResource())
            .build();

    @Test
    public void getAll() throws Exception {
        ClientResponse response = resources.client().resource("/user").get(ClientResponse.class);

        assertEquals(200, response.getStatus());
        List<User> users = response.getEntity(new GenericType<List<User>>(){});
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
    }

    @Test
    public void get() throws Exception {
        ClientResponse response = resources.client().resource("/user/test1").get(ClientResponse.class);

        assertEquals(200, response.getStatus());
        User user = response.getEntity(User.class);
        assertEquals("test1", user.getUsername());
    }

    @Test
    public void update() throws Exception {
        User user = UserTests.getUser();

        ClientResponse response = resources.client().resource("/user/test1")
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, user);

        assertEquals(200, response.getStatus());
        User updatedUser = response.getEntity(User.class);
        assertEquals(user, updatedUser);
    }

    @Test(expected = ConstraintViolationException.class)
    public void update_invalid_user() throws Exception {
        User user = UserTests.getUser().setDisplayName("");

        ClientResponse response = resources.client().resource("/user/test1")
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, user);
    }

    @Test()
    public void add() throws Exception {
        User newUser = UserTests.getUser();

        ClientResponse response = resources.client().resource("/user")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, newUser);

        assertEquals(201, response.getStatus());
        User user = response.getEntity(User.class);
        assertEquals(newUser, user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void add_invalid_user() throws Exception {
        User newUser = UserTests.getUser().setUsername(null);

        ClientResponse response = resources.client().resource("/user")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, newUser);
    }

    @Test()
    public void delete() throws Exception {
        ClientResponse response = resources.client().resource("/user/test1")
                .delete(ClientResponse.class);

        assertEquals(204, response.getStatus());
    }
}
