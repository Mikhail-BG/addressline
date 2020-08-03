# Code Challenge "AddressLine"

## Environment and tools
Application uses:
* **Java 11** (openjdk version "11.0.8" 2020-07-14)
* **Maven:** Apache Maven 3.6.3
* **IDE:** IntelliJ IDEA 2020.2 (Community Edition)

## Assumptions
There are following assumptions regarding provided specifications:
 * input consists of street name and house number only;
 * there are some house number signs could be used, eg: "No";
 * a street name could contain digits but house number starts with special sign or word;
 * house index is only one-sign length;
 * pre-defined signs are being using in input as splitters.

## Test application
>Run `AddressParserTest` to execute tests:
> * with IDE runner;
> * or with Maven: `mvn clean test`.

There are three test-data sets according specification.
Test-data is located in `test/resources/*.csv`:
 * `ComplicatedTest.csv`
 * `OtherCountries.csv`;
 * `SimpleTest.csv`.
 
 Log report: `log/log.log`
