[![Build](https://github.com/blockfrost/blockfrost-java/actions/workflows/build.yml/badge.svg)](https://github.com/blockfrost/blockfrost-java/actions/workflows/build.yml) [![Integration Tests](https://github.com/blockfrost/blockfrost-java/actions/workflows/integration-test.yml/badge.svg)](https://github.com/blockfrost/blockfrost-java/actions/workflows/integration-test.yml)

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
compile 'io.blockfrost:blockfrost-java:$version'
```

**Note:** Replace '$version' with the correct version number

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


#### Cardano Api Usage

- Create a BlockService instance

```
BlockService blockService = new BlockServiceImpl(Constants.BLOCKFROST_TESTNET_URL, PROJECT_ID);
```

- Get Latest Block Details

```
Block block = blockService.getLatestBlock();
```

#### IPFS Api Usage

- Create IPFSService instance

```
IPFSService ipfsService = new IPFSServiceImpl(Constants.BLOCKFROST_IPFS_URL, IPFS_PROJECT_ID);
```

- Add to IPFS, Pin the content and Get content by CID

```
File file = new File(<path to file>);
IPFSObject ipfsObject = ipfsService.add(file);

//pin
PinResponse pinResponse = ipfsService.pinAdd(ipfsObject.getIpfsHash());
       
//get
byte[] bytes = ipfsService.get(ipfsObject.getIpfsHash());
```
#### Release resources when program exits
To release OkHttpClient's thread pool and other resources when the program exits, invoke the following api.

```
NetworkHelper.getInstance().shutdown();
```

### Configuration

A supported configuration property can be set through system property or environment variable. You can also directly set a config property using ConfigHelper.

**Example:**
```
ConfigHelper.INSTANCE.setThreadCount(60);
ConfigHelper.INSTANCE.setRateLimitForPeriod(40);
```

**List of supported config properties**

| Property Name | Description |
| --------------|-------------|
| BF_API_MAX_THREADS | No of threads to use while fetching multiple pages of data in a single request. **Default Value: 10** (Example: getAllAddressUtxos, getAddressTransactions ...)|
| BF_RATE_LIMIT_FOR_PERIOD |  The permission limit for refresh period. This property is used in rate limit implementation. **Default Value: 10** |
| BF_RATE_LIMIT_REFRESH_PERIOD_IN_SEC | The period of limit refresh in sec.  **Default Value: 1** After each period rate limiter sets its permission count to value set for BF_RATE_LIMIT_FOR_PERIOD.|
| BF_RATE_LIMIT_TIMEOUT_DURATION_IN_MILLIS | The default wait for permission duration in milliseconds. **Default Value: 5000**|
| BF_CONNECTION_TIMEOUT | The connect timeout (connection/read/write) for new connections in seconds. **Default Value: 90**|

