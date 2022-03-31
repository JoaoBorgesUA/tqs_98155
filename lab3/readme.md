a) In the A file of tests, givenSetOfEmployees_whenFindAll_thenReturnAllEmployees(), we can find in the last line of code an assertThat with chaining. We can also find in the same file chaining in the line 53, although smaller.
b) In the employee service unit tests (Zone B), in the setUp(), in the mockito.when, it returns a specific result when prompted with a certain query.
c) A mockbean will not only create the mock and substitute it in the context, but it will also substitute any single beans by the mock too.
d) This file contains in which url the integration tests of this spring boot project will run, and some other information that is needed for the project to work, like a username and a password. It is mainly used when a database is needed; being an integration test, those lines are commented because the database will be mocked; still, that line has the information to connect to the mysql database.
e)
