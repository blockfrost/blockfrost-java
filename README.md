[![Java CI with Gradle](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml)

# blockfrost-java

Java SDK for the Blockfrost.io API.

**Compatible with Blockfrost.io OpenAPI version 0.1.26**

## Build

```
$> git clone https://github.com/blockfrost/blockfrost-java.git
$> ./gradlew clean build
```

## Run Integration tests
To run integration tests, you need Blockfrost Project Id for Cardano Testnet.
```
export BF_PROJECT_ID=<Blockfrost Testnet Project Id>
./gradlew integrationTest -PBF_PROJECT_ID=${BF_PROJECT_ID} 
```
