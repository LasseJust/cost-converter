# Dezide cost converter
Cost converter library with command line interface for testing.

## Building the app
To build the app run:
```shell script
./gradlew build
```

Alternatively use the prebuilt .jar file ```costconverter.jar```

## Running the app
Run the jar file, specify at least 3 parameters: time, money, model.

Optional - specify additional custom key/value parameters on the form name:value

```shell script
java -jar build/libs/dezide-cost-converter-1.0-SNAPSHOT.jar <time> <money> <model> [<custom> ...]
```

Example:

Using the jar file built previously:
```shell script
java -jar build/libs/dezide-cost-converter-1.0-SNAPSHOT.jar 0.5 275.50 model1234 inconvenience:none
```

Using the included prebuilt jar file:
```shell script
java -jar costconverter.jar 0.5 275.50 model1234 inconvenience:none
```

## Running tests
To run the tests:
```shell script
./gradlew test
```
