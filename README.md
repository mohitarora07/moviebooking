### Project Structure 
```
.
├── README.md
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── gic
    │               └── moviebooking
    │                   ├── App.java
    │                   ├── constants
    │                   │   └── MenuConstants.java
    │                   ├── model
    │                   │   ├── BookingStatus.java
    │                   │   ├── Seat.java
    │                   │   └── SeatRow.java
    │                   ├── sequence
    │                   │   └── BookingIdGenerator.java
    │                   ├── service
    │                   │   ├── BookingService.java
    │                   │   ├── MenuService.java
    │                   │   └── SeatMapViewer.java
    │                   └── validator
    │                       ├── BookingValidationService.java
    │                       └── PreMenuValidator.java
    └── test
        └── java
            └── com
                └── gic
                    └── moviebooking
                        ├── AppTest.java
                        ├── sequence
                        │   └── BookingIdGeneratorTest.java
                        ├── service
                        └── validator
                            ├── BookingValidationServiceTest.java
                            └── PreMenuValidatorTest.java

20 directories, 17 files
```
### Instructions to Run the Application

1. Unzip the zip folder moviebooking.zip
2. Ensure you have Java 17 and Maven installed on your machine.
3. Build the project using Maven:
   ```
   mvn clean package
   ```
4. Run the application:
   ```
   java -jar target/moviebooking-1.0-SNAPSHOT.jar
   ```
   
### Additional Notes
The project skelton was generated using maven archetype:
```
mvn archetype:generate -DgroupId=com.gic.moviebooking -DartifactId=moviebooking -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
   

