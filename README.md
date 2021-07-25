[![Java CI with Gradle](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml)

# blockfrost-java 
Java SDK for the Blockfrost.io API.

## Build

```
$> git clone https://github.com/blockfrost/blockfrost-java.git
$> ./gradlew clean build
```

## Run Integration tests

```
export BF_PROJECT_ID=<Blockfrost Project Id>
./gradlew integrationTest -PBF_PROJECT_ID=${BF_PROJECT_ID} 
```
