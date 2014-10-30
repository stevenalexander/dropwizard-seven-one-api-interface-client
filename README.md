# Dropwizard 7.1 Response interface

Test project to try and use an interface which defines the response objects for use in a client without losing the ability to return using Response style results in the Resource methods, as these work nicely with attributes like hibernates @unitofwork, which doesn't like WebApplicationException.
