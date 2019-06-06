# Getting Started [city-Search]

This is an application built for Tavisca hiring process.


## Start on local

* Please provision following secrets as environment variables
    - `FOURSQUARE_CLIENT_ID`
    - `FOURSQUARE_CLIENT_SECRET`

* This is a maven project. Running the application is a two-step process
    - `./mvnw clean package` # this will build the application jar
    - `java -jar target/city-search-0.0.1-SNAPSHOT.jar` # This will start the application at port 8080
    

## Querying the application
 - [http://localhost:8080/search?query=Pune](http://localhost:8080/search?query=Pune)
 - [http://localhost:8080/search?query=Pune&category=Historical](http://localhost:8080/search?query=Pune&category=Historical)

