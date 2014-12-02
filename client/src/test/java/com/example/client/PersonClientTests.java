package com.example.client;

import com.example.api.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PersonClientTests {

    PersonClient personClient;

    @Before
    public void setup() {
        Client client = Client.create();
        String baseUri = "http://localhost:8095"; // This should be configured
        personClient = new PersonClient(client, baseUri);
    }

    @Test
    public void getAll() throws Exception {
        List<Person> persons = personClient.getAll();
        assertEquals(2, persons.size());
        assertEquals("person1", persons.get(0).getName());
    }

    @Test
    public void get() throws Exception {
        Person person = personClient.get(1);
        assertEquals("person1", person.getName());
    }

    @Test
    public void update() throws Exception {
        Person person = getPerson();

        Person updatedPerson = personClient.update(1, person);

        assertEquals(person, updatedPerson);
    }

    @Test()
    public void add() throws Exception {
        Person newPerson = getPerson();

        Person person = personClient.add(newPerson);

        assertEquals(newPerson, person);
    }

    @Test
    public void add_invalid_person() throws Exception {
        Person newPerson = getPerson().setName(null);

        try {
            personClient.add(newPerson);
        } catch (UniformInterfaceException exception) {
            if (exception.getResponse().getStatus() != 422) {
                fail("expected 422 for invalid person");
            }
        }
    }

    @Test()
    public void delete() throws Exception {
        personClient.delete(1);
    }

    public static Person getPerson() {
        return new Person()
                .setId(10)
                .setName("person10");
    }
}
