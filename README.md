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

## TODO : Remaining Task 
* This application currently only fetches data from FourSquare API
* To add another source, we just need to add a class which implements `com.tavisca.citysearch.sources.LocationInfoSource` interface. This class will be automatically dependency injected by Spring.
* Only few fields are mapped to service response. Additional fields can be mapped based on requirements. Class to change is `com.tavisca.citysearch.sources.foursquare.FoursquareResponseMapper`
* Error handling is only minimal at this point.
* Code need to refactor in order to make this clean