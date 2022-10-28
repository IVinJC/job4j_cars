# WEB Application - "Car sale platform"

<p>This project represents Web Application on Spring Book Framework</p>

### Functionality:

* There are advertisements on the website.
* Advertisements have description, brand of car, body type, photo, author
* Advertisement has status sold or not
* Home page shows all ads
* Home page has link for create new Advertisement
* By clicking on the advertisement name, we can go to the detailed description page,
  where it is possible to delete the ad, change the status to sold and return to the home page
* Ability to run application using Docker Compose

### Assembly and installation:

1. Create "carsale_db" database or your own database
2. Change your PostgreSQL database username, password or url database in 'src/main/resources/hibernate.cfg.xml'
   and 'db/liquibase.properties'
3. Using Spring Boot CLI

```shell
mvn spring-boot:run -Dspring-boot.run.arguments=--db=your_database,--user=your_user,--password=your_password,--port=your_port
```

OR using default properties(db=carsale_db, user=postgres, password=password, port=5432)

```shell
mvn spring-boot:run
```

3. Go to http://localhost:8080/index.html

### TODO list

The program can:

1. Create, read, delete tasks Advertisements
2. Has an interface for managing and modifying Advertisements
3. Added Junit testing

## Application launch via Docker Compose

* Cloning the project git clone https://github.com/IVinJC/job4j_cars.git
* Putting together a project mvn install
* create Dockerfile \
  _FROM openjdk \
  WORKDIR cars \
  ADD target/job4j_cars-1.0-SNAPSHOT.jar app.jar \
  ENTRYPOINT java -jar app.jar_
* To create docker compose, copy from 'src/main/resources/docker-compose.yml'
* LAUNCH docker-compose up

### Used technologies:

![](https://img.shields.io/badge/Java-18.0-blueviolet)
![](https://img.shields.io/badge/SpringBoot-2.7.2-important)
![](https://img.shields.io/badge/Postgresql-42.4.2-green)
![](https://img.shields.io/badge/Hibernate-5.6.11-green)
![](https://img.shields.io/badge/h2database-2.1.214-brightgreen)
![](https://img.shields.io/badge/Thymeleaf-blue)
![](https://img.shields.io/badge/Liquibase-4.15-critical)
![](https://img.shields.io/badge/Junit-4-critical)
![](https://img.shields.io/badge/Maven-3.8.5-critical)

### Contact:
If you have any questions, feel free to contact me https://t.me/VadimDedeyko

<!-- 
brightgreen
green
yellowgreen
yellow
orange
red
blue
lightgrey
success
important
critical
informational
inactive
blueviolet
ff69b4
9cf 
-->