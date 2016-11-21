# MowItNow

## Test

A dedicated unit test (MowItApplicationTests) will show the expected result according to the specification file.

```bash
# Unix
./mvnw clean test -Dtest=MowItNowApplicationTests

# Windows
./mvnw.cmd test -Dtest=MowItNowApplicationTests
```

> Other unit tests are present to show that each parts of the program are correct.

## Compile and run the program

* UNIX
```bash
# Unix
./mvnw clean package
java -jar target/mowitnow-0.0.1-SNAPSHOT.jar --mow.input-file="/path/to/instructions.txt"

#Windows
./mvnw.cmd clean package
java -jar target/mowitnow-0.0.1-SNAPSHOT.jar --mow.input-file="/path/to/instructions.txt"
```

## Program details

* To show more details on the console, you can use the program argument 
`--logging.level.com.github.herau=DEBUG`