package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.MetadataServiceImpl;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetadataServiceTests extends TestBase {

    MetadataService metadataService;

    @BeforeEach
    public void setup(){
        metadataService = new MetadataServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }


    @Nested
    @DisplayName("GetTransactionMetadataLabels Tests")
    class GetTransactionMetadataLabelsTests {

        @Test
        public void transactionMetadataLabels_willReturn_transactionMetadataLabelsForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabel> expectedTransactionMetadataLabelsList = Arrays.asList(
                    TransactionMetadataLabel.builder().label("0").cip10(null).count("27471").build(),
                    TransactionMetadataLabel.builder().label("1").cip10(null).count("4454").build(),
                    TransactionMetadataLabel.builder().label("2").cip10(null).count("4185").build()
            );

            List<TransactionMetadataLabel> metadataList = metadataService.getTransactionMetadataLabels(3, 1, OrderEnum.asc);

            assertThat(metadataList, hasSize(3));
            assertThat(metadataList, contains(expectedTransactionMetadataLabelsList.toArray()));

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
            assertThat(metadataList, contains(expectedTransactionMetadataLabelsList.toArray()));
        }

        @Test
        public void transactionMetadataLabels_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataLabels(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }

}
