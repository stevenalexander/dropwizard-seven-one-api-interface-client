package com.example.client;

import com.example.api.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class PersonClient {

    Client client;
    WebResource personResource;

    public PersonClient(Client client, String baseUri) {
        this.client = client;
        personResource = client.resource(baseUri + "/person");
    }


    public List<Person> getAll() {
        return personResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Person>>() {});
    }

    public Person get(Integer id) {
        return personResource
                .path(id.toString())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Person.class);
    }

    public Person add(Person person) {
        return personResource
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Person.class, person);
    }

    public Person update(Integer id, Person person) {
        return personResource
                .path(id.toString())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Person.class, person);
    }

    public void delete(Integer id) {
        personResource
                .path(id.toString())
                .delete();
    }
}
