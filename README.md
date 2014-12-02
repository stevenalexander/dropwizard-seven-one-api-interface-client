# Dropwizard 7.1 Response interface

Test project to use gradle to build a dropwizard project split into api, application and client sub-projects so the representation classes held in api can be shared between application and client or any other project using the dropwizard application.

Requires:

* [gradle](http://www.gradle.org/)

## Setup

To run:

```
./go
```

To compile:

```
gradle compileJava # compiles all projects
gradle :application:oneJar # creates standalone jar for dropwizard application
```

To test:

```
gradle test # requires local running instance of dropwizard application for client integration tests

# individual project tests
gradle :api:test
gradle :application:test
gradle :client:test
```

## Note

* Importing the project into Intellij using the Gradle import will not pick up the project level language and cause
error "java: diamond operator is not supported in -source 1.6 (use -source 7 or higher to enable diamond operator)".
You must manually change the project language level to Java 7 by "Project Structure" - "Project" - "Project language level"