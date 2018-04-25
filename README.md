# PA036
=========
## Installation
`mvn clean install`

## Running
`mvn spring-boot:run`
> App is available on [localhost:8080](http://localhost:8080)

For now we expose 3 REST endpoints:
- `/book` - books - Standard REST, look at `BookController.java`
- `/author` - authors - Standard REST, look at `AuthorController.java`
- `/publisher` - publishers - Standard REST, look at `PublisherController.java`

## Implementation details
App is based on **Spring**, **H2 DB**, **Hibernate** and **Ehcache**.
Caching for now does **not** consider joins.
> Cached entity is wiped out if it is not touched for 10s
