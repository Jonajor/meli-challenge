# Fogo de Quasar
# Creating Rest APIs with Spring and H2

## Requirements

For building and running the application you need:

- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.h2database.com/html/main.html)
- [Docker](https://www.docker.com/)

## Executing requests

[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/99cb00289cad80458465)

## Exploring the Rest APIs

The application defines following REST APIs

```
1. POST /topsecret - Return location and message in order (Nível 2)

2. POST /topsecret_split/{satellite_name} - Save ship information (Nível 3)

3. GET /topsecret_split/{satellite_name} - search ship and return as subscription level 2 (Nǘel 3)

4. GET /topsecret_split - Search all ships in base and return level 2 signature
```

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Jonajor/meli-challenge.git
cd meli-challenge
```

**2. You can also build and run the application using maven**

***3.1. Database
In this project I used H2 in-memory, you can see h2 dashboard running [this link](http://localhost:8080/h2-console/) after to runner the application local.***

```

***3.2. Run the application using maven***
```bash
mvn spring-boot:run
```

The server will start at <http://localhost:8080>.

## Deploy
The application is hosted on [Heroku](https://id.heroku.com/login)
```
1. POST https://quasar-war-meli.herokuapp.com/topsecret

2. POST https://quasar-war-meli.herokuapp.com/topsecret_split/{satellite_name}

3. GET https://quasar-war-meli.herokuapp.com/topsecret_split

4. GET https://quasar-war-meli.herokuapp.com/topsecret_split/{satellite_name}
```

## Running integration tests

The project also contains integration tests for all the Rest APIs. For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.
```shell
mvn test
```

## Built With

- [Java](https://www.java.com/pt-BR/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Spring](https://spring.io/) - Java Framework
- [Maven](https://maven.apache.org/) - Dependency Management
- [H2](https://www.h2database.com/html/main.html) - DataBase
- [Docker](https://www.docker.com/) - Containerization Platform
- [Heroku](https://id.heroku.com/login) - Provedor Cloud
