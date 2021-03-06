package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Network;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.NetworkServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NetworkServiceTests extends TestBase {

    static NetworkService networkService;

    @BeforeAll
    public static void setup() {
        networkService = new NetworkServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void network_willReturn_networkInfo() throws APIException {

        //TODO: This changes frequently. Can we rely on max networkSupply or do we need to remove that also.

//        Network expectedNetwork = Network.builder()
//                .networkStake( NetworkStake.builder()
//                        .live("14278864583150094")
//                        .active("13690202531062820")
//                        .build())
//                .networkSupply( NetworkSupply.builder()
//                        .max("45000000000000000")
//                        .total("40236648947239373")
//                        .circulating("42051490630229286")
//                        .build())
//                .build();

        Network networkResponse = networkService.getNetwork();
        assertThat(networkResponse.getSupply().getMax(), is("45000000000000000"));
    }

}
