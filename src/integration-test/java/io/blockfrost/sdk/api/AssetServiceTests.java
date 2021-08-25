package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssetServiceTests extends TestBase {

    AssetService assetService;

    @BeforeEach
    public void setup() {
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

        @Test
        public void getAssets_willReturn_allAssets() throws APIException {

            List<Asset> assetList = assetService.getAssets();
            assertThat(assetList, hasSize(greaterThanOrEqualTo(0)));

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

            List<AssetHistory> assetHistoryList = assetService.getAssetHistory("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
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

            List<AssetTransaction> assetTransactionList = assetService.getAssetTransactions("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
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

            List<AssetAddress> assetAddressList = assetService.getAssetAddresses("476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c36e7574636f696e");
            assertThat(assetAddressList, hasSize(greaterThanOrEqualTo(0)));
        }

        @Test
        public void getAssetAddresses_willThrowAPIException_onNullAsset() {

            Exception exception = assertThrows(APIException.class, () -> assetService.getAssetAddresses(null, 5, 1));
            assertThat(exception.getMessage(), is("Asset cannot be null or empty"));
        }

    }

}
