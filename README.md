# MowItNow

## Web version

A web version is available on the localhost:8080 with the command below: 

```bash
# Unix
./mvnw spring-boot:run

# Windows
./mvnw.cmd spring-boot:run
```

This allow you to select the input file and observe the final position of the mowers.

> The port number can be changed with the JVM environment variable: `-Dserver.port` 

## Console version

A dedicated unit test (MowItApplicationTests) will show the expected result in the console, according to the specification file.

```bash
# Unix
./mvnw clean test -Dtest=MowItNowApplicationTests

# Windows
./mvnw.cmd test -Dtest=MowItNowApplicationTests
```

> Other unit tests are present to show that each parts of the program are correct.

## Compilation and run details

```bash
# Unix
./mvnw clean package
java -jar target/mowitnow-0.0.1-SNAPSHOT.jar --mower.input-file="/path/to/instructions.txt"

#Windows
./mvnw.cmd clean package
java -jar target/mowitnow-0.0.1-SNAPSHOT.jar --mower.input-file="/path/to/instructions.txt"
```

## Program details

* To show more details on the console, you can use the program argument 
`--logging.level.com.github.herau=DEBUG`