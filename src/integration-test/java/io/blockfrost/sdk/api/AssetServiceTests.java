package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Asset;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AssetServiceTests extends TestBase {

    AssetService assetService;

    @BeforeEach
    public void setup(){
        assetService = new AssetServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void getAsset_willReturn_asset() throws APIException {

        Asset expectedAsset = Asset.builder()
                .asset("07a6604234b758be257f26565445f30169c25c85cf392797bc878de753554d")
                .policyId("07a6604234b758be257f26565445f30169c25c85cf392797bc878de7")
                .assetName("53554d")
                .fingerprint("asset1jjfl57h6uyf4fw8wzeyzwmtly7ptmjpy36ugqk")
                .quantity("1200000")
                .initialMintTxHash("35de2138b3b84f9a0eb2de321a88eca2163b0077e4e3bcb9d6e7bd2922e746e7")
                .mintOrBurnCount(1)
                .onchainMetadata(null)
                .metadata(null)
                .build();

        Asset asset = assetService.getAsset("07a6604234b758be257f26565445f30169c25c85cf392797bc878de753554d");
        assertThat(asset, is(expectedAsset));
    }

}
