[![Java CI with Gradle](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/blockfrost/blockfrost-java/actions/workflows/gradle.yml)

# blockfrost-java

Java SDK for the Blockfrost.io API.

**Compatible with Blockfrost.io OpenAPI version 0.1.26**


## Getting started

To use this SDK, you first need to log in to [blockfrost.io](https://blockfrost.io), create your project and retrieve the API token.

<img src="https://i.imgur.com/smY12ro.png">

<br/>

## Build

```
$> git clone https://github.com/blockfrost/blockfrost-java.git
$> ./gradlew clean build
```

## Publish to Local Maven Repository

```
$> ./gradlew publishToMavenLocal
```

## Run Integration tests
To run integration tests, you need Blockfrost Project Id for Cardano Testnet.
```
export BF_PROJECT_ID=<Blockfrost Cardano Testnet Project Id>
export BF_IPFS_PROJECT_ID=<Blockfrost Ipfs Project Id>

./gradlew integrationTest -PBF_PROJECT_ID=${BF_PROJECT_ID} -PBF_IPFS_PROJECT_ID=${BF_IPFS_PROJECT_ID}
```

## How to use ?

### Add dependency 

- For Maven, add the following dependency to project's pom.xml

```
    <dependencies>
        <dependency>
            <groupId>io.blockfrost</groupId>
            <artifactId>blockfrost-java</artifactId>
            <version>$version</version>
        </dependency>
    </dependencies>
```

- For Gradle, add the following dependency to build.gradle

```
compile 'com.bloxbean.cardano:cardano-client-lib:$version'
```

**Note:** Replace '$version' with correct version number

**For example:** $version = 0.1.0-SNAPSHOT (If you have published snapshot jar to your local maven repository)

### Usage

#### Following services are available

- **Cardano**
    - AccountService
    - AddressService
    - AssetService
    - BlockService
    - EpochService
    - HealthService
    - LedgerService
    - MetadataService
    - NetworkService
    - PoolService
    - TransactionService
  - NutLinkService
- **IPFS**
    - IPFSService


#### Cardano BlockService Usage

- Create a BlockService instance

```
BlockService blockService = new BlockServiceImpl(Constants.BLOCKFROST_TESTNET_URL, PROJECT_ID);
```

- Get Latest Block Details

```
Block block = blockService.getLatestBlock();
```


