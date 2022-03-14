package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.AssetServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssetServiceTests extends TestBase {

    static AssetService assetService;

    @BeforeAll
    public static void setup() {
        assetService = new AssetServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Nested
    @DisplayName("GetAssets Tests")
    class GetAssets {
        @Test
        public void getAssets_willReturn_assetsForCountPageAndOrder() throws APIException {

            Asset expectedAsset = Asset.builder()
                    .asset("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e")
                    .quantity("1")
                    .build();

            List<Asset> assetList = assetService.getAssets(5, 1, OrderEnum.asc);
            assertThat(assetList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetList, hasItem(expectedAsset));
        }

        @Test
        public void getAssets_willReturn_assetsForCountAndPage() throws APIException {

            Asset expectedAsset = Asset.builder()
                    .asset("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e")
                    .quantity("1")
                    .build();

            List<Asset> assetList = assetService.getAssets(5, 1);
            assertThat(assetList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetList, hasItem(expectedAsset));
        }
    }

    @Nested
    @DisplayName("GetAsset Tests")
    class GetAsset {
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

        @Test
        public void getAsset_willReturn_assetWithOnchainMetadata() throws APIException {
            final String filesKey = "files";

            Map<String, String> expectedFiles = new HashMap<>();
            expectedFiles.put("src", "ipfs://QmaotSicXXa9LzpYKfkt6SWhgTVrRGyBJbTWAgDA1sVBXH");
            expectedFiles.put("name", "FreeGhost01853");
            expectedFiles.put("https", "");
            expectedFiles.put("mediaType", "image/jpeg");

            Map<String, Object> expectedOnchainMetadata = new HashMap<>();
            expectedOnchainMetadata.put("dna", "00013000");
            expectedOnchainMetadata.put("back", "College Backpack");
            expectedOnchainMetadata.put("body", "Blue Shirt");
            expectedOnchainMetadata.put("eyes", "Normal");
            expectedOnchainMetadata.put("name", "FreeGhost01853");
            expectedOnchainMetadata.put(filesKey, Collections.singleton(expectedFiles));
            expectedOnchainMetadata.put("ghost", "Normal");
            expectedOnchainMetadata.put("image", "ipfs://QmaotSicXXa9LzpYKfkt6SWhgTVrRGyBJbTWAgDA1sVBXH");
            expectedOnchainMetadata.put("rarity", "Epic");
            expectedOnchainMetadata.put("project", "FreeGhost");
            expectedOnchainMetadata.put("twitter", "twitter.com/freeroam_io");
            expectedOnchainMetadata.put("website", "FREEROAM TESTING");
            expectedOnchainMetadata.put("mediaType", "image/jpeg");

            Asset asset = assetService.getAsset("3f5265ef14f89e948fd5b5f55419712ad1f0dd4d75ab26be134441714672656547686f73743031383533");
            Map<String, Object> onchainMetadata = asset.getOnchainMetadata();

            assertThat(onchainMetadata, notNullValue());
            expectedOnchainMetadata.entrySet().stream()
                    .filter(e -> !e.getKey().equals(filesKey))
                    .forEach(e -> assertThat(onchainMetadata, hasEntry(e.getKey(), e.getValue())));

            assertThat(onchainMetadata.get(filesKey), instanceOf(List.class));
            @SuppressWarnings("unchecked")
            List<Map<String, String>> files = (List<Map<String, String>>) onchainMetadata.get(filesKey);
            expectedFiles.forEach((key, value) -> assertThat(files.get(0), hasEntry(key, value)));
        }

        @Test
        public void getAsset_willThrowAPIException_onNullAsset() {

            Exception exception = assertThrows(APIException.class, () -> assetService.getAsset(null));
            assertThat(exception.getMessage(), is("Asset cannot be null or empty"));
        }
    }

    @Nested
    @DisplayName("GetAssetHistory Tests")
    class GetAssetHistory {
        @Test
        public void getAssetHistory_willReturn_assetHistoryForCountPageAndOrder() throws APIException {

            AssetHistory expectedAssetHistory = AssetHistory.builder()
                    .txHash("e067ca567df4920f4ac3babc4d805d2afe860e21aa7f6f78dbe8538caf9d8262")
                    .amount("1")
                    .action(AssetAction.minted.name())
                    .build();

            List<AssetHistory> assetHistoryList = assetService.getAssetHistory("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1, OrderEnum.asc);
            assertThat(assetHistoryList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetHistoryList, hasItem(expectedAssetHistory));
        }

        @Test
        public void getAssetHistory_willReturn_assetHistoryForCountAndPage() throws APIException {

            AssetHistory expectedAssetHistory = AssetHistory.builder()
                    .txHash("e067ca567df4920f4ac3babc4d805d2afe860e21aa7f6f78dbe8538caf9d8262")
                    .amount("1")
                    .action(AssetAction.minted.name())
                    .build();

            List<AssetHistory> assetHistoryList = assetService.getAssetHistory("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1);
            assertThat(assetHistoryList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetHistoryList, hasItem(expectedAssetHistory));
        }

        @Test
        public void getAssetHistory_willReturn_allAssetHistory() throws APIException {

            List<AssetHistory> assetHistoryList = assetService.getEntireAssetHistory("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
            assertThat(assetHistoryList, hasSize(greaterThanOrEqualTo(0)));

        }

        @Test
        public void getAssetHistory_willThrowAPIException_onNullAsset() {

            Exception exception = assertThrows(APIException.class, () -> assetService.getAssetHistory(null, 5, 1));
            assertThat(exception.getMessage(), is("Asset cannot be null or empty"));
        }
    }

    @Nested
    @DisplayName("GetAssetTransactions Tests")
    class GetAssetTransactions {

        @Test
        public void getAssetTransaction_willReturn_assetTransactionForCountPageAndOrder() throws APIException {

            AssetTransaction expectedAssetTransaction = AssetTransaction.builder()
                    .txHash("e067ca567df4920f4ac3babc4d805d2afe860e21aa7f6f78dbe8538caf9d8262")
                    .blockHeight(2287021)
                    .txIndex(0)
                    .build();

            List<AssetTransaction> assetTransactionList = assetService.getAssetTransactions("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1, OrderEnum.asc);
            assertThat(assetTransactionList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetTransactionList, hasItem(expectedAssetTransaction));
        }

        @Test
        public void getAssetTransaction_willReturn_assetTransactionForCountAndPage() throws APIException {

            AssetTransaction expectedAssetTransaction = AssetTransaction.builder()
                    .txHash("e067ca567df4920f4ac3babc4d805d2afe860e21aa7f6f78dbe8538caf9d8262")
                    .blockHeight(2287021)
                    .txIndex(0)
                    .build();

            List<AssetTransaction> assetTransactionList = assetService.getAssetTransactions("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1);
            assertThat(assetTransactionList, hasSize(lessThanOrEqualTo(5)));
            assertThat(assetTransactionList, hasItem(expectedAssetTransaction));
        }

        @Test
        public void getAssetTransaction_willReturn_allAssetTransactions() throws APIException {

            List<AssetTransaction> assetTransactionList = assetService.getAllAssetTransactions("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
            assertThat(assetTransactionList, hasSize(greaterThanOrEqualTo(0)));

        }

        @Test
        public void getAssetTransaction_willThrowAPIException_onNullAsset() {

            Exception exception = assertThrows(APIException.class, () -> assetService.getAssetTransactions(null, 5, 1));
            assertThat(exception.getMessage(), is("Asset cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAssetAddresses Tests")
    class GetAssetAddresses {

        @Test
        public void getAssetAddresses_willReturn_assetAddressesForCountPageAndOrder() throws APIException {

            AssetAddress expectedAssetAddress = AssetAddress.builder()
                    .address("addr_test1qrus2mgpuv3nqvmusfszhhy0pyk8m92qgnfng3s3kj6vwndre5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09q5z0f76")
                    .quantity("1")
                    .build();

            List<AssetAddress> assetAddressList = assetService.getAssetAddresses("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1, OrderEnum.asc);
            assertThat(assetAddressList, hasSize(lessThanOrEqualTo(5)));
        }

        @Test
        public void getAssetAddresses_willReturn_assetAddressesForCountAndPage() throws APIException {

            AssetAddress expectedAssetAddress = AssetAddress.builder()
                    .address("addr_test1qrus2mgpuv3nqvmusfszhhy0pyk8m92qgnfng3s3kj6vwndre5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09q5z0f76")
                    .quantity("1")
                    .build();

            List<AssetAddress> assetAddressList = assetService.getAssetAddresses("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e", 5, 1);
            assertThat(assetAddressList, hasSize(lessThanOrEqualTo(5)));
        }

        @Test
        public void getAssetAddresses_willReturn_allAssetAddresses() throws APIException {

            List<AssetAddress> assetAddressList = assetService.getAllAssetAddresses("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
            assertThat(assetAddressList, hasSize(greaterThanOrEqualTo(0)));
        }

        @Test
        public void getAssetAddresses_willThrowAPIException_onNullAsset() {

            Exception exception = assertThrows(APIException.class, () -> assetService.getAssetAddresses(null, 5, 1));
            assertThat(exception.getMessage(), is("Asset cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetPolicyAssets Tests")
    class GetPolicyAssets {

        @Test
        public void getPolicyAssets_willReturn_policyAssetsForCountPageAndOrder() throws APIException {
            List<PolicyAsset> policyAssetsList = assetService.getPolicyAssets("07a6604234b758be257f26565445f30169c25c85cf392797bc878de7", 5, 1, OrderEnum.asc);
            assertThat(policyAssetsList, hasSize(lessThanOrEqualTo(5)));
        }

        @Test
        public void getPolicyAssets_willReturn_policyAssetsForCountAndPage() throws APIException {
            List<PolicyAsset> policyAssetsList = assetService.getPolicyAssets("07a6604234b758be257f26565445f30169c25c85cf392797bc878de7", 5, 1, OrderEnum.asc);
            assertThat(policyAssetsList, hasSize(lessThanOrEqualTo(5)));
        }

        @Test
        public void getPolicyAssets_willReturn_allPolicyAssets() throws APIException {
            List<PolicyAsset> policyAssetsList = assetService.getAllPolicyAssets("07a6604234b758be257f26565445f30169c25c85cf392797bc878de7");
            assertThat(policyAssetsList, hasSize(greaterThanOrEqualTo(0)));
        }

        @Test
        public void getPolicyAssets_willThrowAPIException_onNullPolicyId() {
            Exception exception = assertThrows(APIException.class, () -> assetService.getPolicyAssets(null, 5, 1));
            assertThat(exception.getMessage(), is("PolicyId cannot be null or empty"));
        }

    }

}
