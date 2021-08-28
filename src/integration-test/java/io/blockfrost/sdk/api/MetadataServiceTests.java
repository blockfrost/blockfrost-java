package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelCbor;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelJson;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.MetadataServiceImpl;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetadataServiceTests extends TestBase {

    static MetadataService metadataService;

    @BeforeAll
    public static void setup() {
        metadataService = new MetadataServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }


    @Nested
    @DisplayName("GetTransactionMetadataLabels Tests")
    class GetTransactionMetadataJsonLabelsTests {

        @Test
        public void transactionMetadataLabels_willReturn_transactionMetadataLabelsForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabel> expectedTransactionMetadataLabelsList = Arrays.asList(
                    TransactionMetadataLabel.builder().label("0").cip10(null).count("27471").build(),
                    TransactionMetadataLabel.builder().label("1").cip10(null).count("4454").build(),
                    TransactionMetadataLabel.builder().label("2").cip10(null).count("4185").build()
            );

            List<TransactionMetadataLabel> metadataList = metadataService.getTransactionMetadataLabels(3, 1, OrderEnum.asc);

            assertThat(metadataList, hasSize(3));
            assertThat(metadataList.get(0), samePropertyValuesAs(expectedTransactionMetadataLabelsList.get(0), "count"));

        }

        @Test
        public void transactionMetadataLabels_willReturn_transactionMetadataLabelsForCountPage() throws APIException {

            List<TransactionMetadataLabel> expectedTransactionMetadataLabelsList = Arrays.asList(
                    TransactionMetadataLabel.builder().label("0").cip10(null).count("27471").build(),
                    TransactionMetadataLabel.builder().label("1").cip10(null).count("4454").build(),
                    TransactionMetadataLabel.builder().label("2").cip10(null).count("4185").build()
            );

            List<TransactionMetadataLabel> metadataList = metadataService.getTransactionMetadataLabels(3, 1, OrderEnum.asc);

            assertThat(metadataList, hasSize(3));
            assertThat(metadataList.get(0), samePropertyValuesAs(expectedTransactionMetadataLabelsList.get(0), "count"));

        }

        @Test
        @Disabled
        public void transactionMetadataLabels_willReturn_allTransactionMetadataLabels() throws APIException {

            List<TransactionMetadataLabel> metadataList = metadataService.getAllTransactionMetadataLabels();
            assertThat(metadataList, hasSize(greaterThanOrEqualTo(0)));

        }

        @Test
        public void transactionMetadataLabels_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataLabels(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }

    @Nested
    @DisplayName("GetTransactionMetadataJsonCborForLabel Tests")
    class GetTransactionMetadataJsonCborForLabel {

        @Test
        public void transactionMetadataCborForLabel_willReturn_transactionMetadataLabelCborForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabelCbor> expectedTransactionMetadataLabelCborList = Arrays.asList(
                    TransactionMetadataLabelCbor.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build()
            );

            List<TransactionMetadataLabelCbor> transactionMetadataLabelCborList = metadataService.getTransactionMetadataCborForLabel("0", 3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelCborList, hasSize(3));
            assertThat(transactionMetadataLabelCborList, contains(expectedTransactionMetadataLabelCborList.toArray()));

        }

        @Test
        public void transactionMetadataCborForLabel_willReturn_transactionMetadataLabelCborForCountPage() throws APIException {

            List<TransactionMetadataLabelCbor> expectedTransactionMetadataLabelCborList = Arrays.asList(
                    TransactionMetadataLabelCbor.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build()
            );

            List<TransactionMetadataLabelCbor> transactionMetadataLabelCborList = metadataService.getTransactionMetadataCborForLabel("0", 3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelCborList, hasSize(3));
            assertThat(transactionMetadataLabelCborList, contains(expectedTransactionMetadataLabelCborList.toArray()));

        }

        //TODO: Some issue with the underlying API. The response is always 404 after some pages. Need to check.
        @Test
        @Disabled
        public void transactionMetadataCborForLabel_willReturn_allTransactionMetadataLabelCbors() throws APIException {

            List<TransactionMetadataLabelCbor> transactionMetadataLabelCborList = metadataService.getAllTransactionMetadataCborForLabel("100");
            assertThat(transactionMetadataLabelCborList, hasSize(greaterThanOrEqualTo(0)));

        }

        @Test
        public void transactionMetadataCborForLabel_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataCborForLabel("0", 101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

        @Test
        public void transactionMetadataCborForLabel_willThrowAPIException_onNullLabel() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getAllTransactionMetadataCborForLabel(null));
            assertThat(exception.getMessage(), is("Label cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetTransactionMetadataJsonJsonForLabel Tests")
    class GetTransactionMetadataJsonJsonForLabel {

        @Test
        public void transactionMetadataJsonForLabel_willReturn_transactionMetadataLabelJsonForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabelJson> expectedTransactionMetadataLabelJsonList = Arrays.asList(
                    TransactionMetadataLabelJson.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .jsonMetadata("cardano")
                            .build(),
                    TransactionMetadataLabelJson.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .jsonMetadata("cardano")
                            .build(),
                    TransactionMetadataLabelJson.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .jsonMetadata("cardano")
                            .build()
            );

            List<TransactionMetadataLabelJson> transactionMetadataLabelJsonList = metadataService.getTransactionMetadataJsonForLabel("0", 3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelJsonList, hasSize(3));
            assertThat(transactionMetadataLabelJsonList, contains(expectedTransactionMetadataLabelJsonList.toArray()));

        }

        @Test
        public void transactionMetadataJsonForLabel_willReturn_transactionMetadataLabelJsonForCountPage() throws APIException {

            List<TransactionMetadataLabelJson> expectedTransactionMetadataLabelJsonList = Arrays.asList(
                    TransactionMetadataLabelJson.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .jsonMetadata("cardano")
                            .build(),
                    TransactionMetadataLabelJson.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .jsonMetadata("cardano")
                            .build(),
                    TransactionMetadataLabelJson.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .jsonMetadata("cardano")
                            .build()
            );

            List<TransactionMetadataLabelJson> transactionMetadataLabelJsonList = metadataService.getTransactionMetadataJsonForLabel("0", 3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelJsonList, hasSize(3));
            assertThat(transactionMetadataLabelJsonList, contains(expectedTransactionMetadataLabelJsonList.toArray()));

        }

        //TODO: Some issue with the underlying API. The response is always 404 after some pages. Need to check.
        @Test
        @Disabled
        public void transactionMetadataJsonForLabel_willReturn_allTransactionMetadataLabelJson() throws APIException {

            List<TransactionMetadataLabelJson> transactionMetadataLabelJsonList = metadataService.getAllTransactionMetadataJsonForLabel("10");
            assertThat(transactionMetadataLabelJsonList, hasSize(greaterThanOrEqualTo(0)));

        }

        @Test
        public void transactionMetadataJsonForLabel_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataJsonForLabel("0", 101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

        @Test
        public void transactionMetadataJsonForLabel_willThrowAPIException_onNullLabel() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataJsonForLabel(null, 1, 1));
            assertThat(exception.getMessage(), is("Label cannot be null or empty"));

            exception = assertThrows(APIException.class, () -> metadataService.getAllTransactionMetadataJsonForLabel(null));
            assertThat(exception.getMessage(), is("Label cannot be null or empty"));

        }

    }

}
