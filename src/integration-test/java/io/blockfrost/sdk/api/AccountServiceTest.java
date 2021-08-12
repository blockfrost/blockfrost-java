package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest extends TestBase {

    AccountService accountService;

    @BeforeEach
    public void setup(){
        accountService = new AccountServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void getAccountByStakeAddress_willReturn_Account() throws APIException {
        String stakeAddress = "stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha";
        Account account = accountService.getAccountByStakeAddress(stakeAddress);

        assertThat(account.getStakeAddress(), is("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha"));
        assertTrue(account.getActive());
        assertNotNull(account.getPoolId());
    }

    @Test
    public void getAccountByStakeAddress_willThrowAPIException_onNullPoolId() {

        Exception exception = assertThrows(APIException.class, () -> accountService.getAccountByStakeAddress(null));
        assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
    }

    @Nested
    @DisplayName("GetAccountHistory Tests")
    class GetAccountHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountHistory> historyList = accountService.getAccountHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
            assertThat(historyList, hasItem(allOf(hasProperty("poolId", is(notNullValue())))));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountHistory> historyList = accountService.getAccountHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
            assertThat(historyList, hasItem(allOf(hasProperty("poolId", is(notNullValue())))));
        }

        @Test
        public void history_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountRewardHistory Tests")
    class GetAccountRewardHistory {

        @Test
        public void rewardHistory_willReturn_rewardHistoryForCountPageAndAscendingOrder() throws APIException {

            List<AccountRewardHistory> rewardHistoryList = accountService.getAccountRewardHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(rewardHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void rewardHistory_willReturn_rewardHistoryForCountAndPage() throws APIException {

            List<AccountRewardHistory> rewardHistoryList = accountService.getAccountRewardHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(rewardHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void rewardHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountRewardHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountDelegationHistory Tests")
    class GetAccountDelegationHistory {

        @Test
        public void delegationHistory_willReturn_delegationHistoryForCountPageAndAscendingOrder() throws APIException {

            List<AccountDelegationHistory> delegationHistoryList = accountService.getAccountDelegationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(delegationHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void delegationHistory_willReturn_delegationHistoryForCountAndPage() throws APIException {

            List<AccountDelegationHistory> delegationHistoryList = accountService.getAccountDelegationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(delegationHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void delegationHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountDelegationHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountRegistrationHistory Tests")
    class GetAccountRegistrationHistory {

        @Test
        public void registrationHistory_willReturn_registrationHistoryForCountPageAndAscendingOrder() throws APIException {

            List<AccountRegistrationHistory> registrationHistoryList = accountService.getAccountRegistrationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(registrationHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void registrationHistory_willReturn_registrationHistoryForCountAndPage() throws APIException {

            List<AccountRegistrationHistory> registrationHistoryList = accountService.getAccountRegistrationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(registrationHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void registrationHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountRegistrationHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountWithdrawalHistory Tests")
    class GetAccountWithdrawalHistory {

        @Test
        public void withdrawalHistory_willReturn_withdrawalHistoryForCountPageAndAscendingOrder() throws APIException {

            List<AccountWithdrawalHistory> withdrawalHistoryList = accountService.getAccountWithdrawalHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(withdrawalHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void withdrawalHistory_willReturn_withdrawalHistoryForCountAndPage() throws APIException {

            List<AccountWithdrawalHistory> withdrawalHistoryList = accountService.getAccountWithdrawalHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(withdrawalHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void withdrawalHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountWithdrawalHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountMirHistory Tests")
    class GetAccountMirHistory {

        @Test
        public void mirHistory_willReturn_mirHistoryForCountPageAndAscendingOrder() throws APIException {

            List<AccountMirHistory> mirHistoryList = accountService.getAccountMirHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(mirHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void mirHistory_willReturn_mirHistoryForCountAndPage() throws APIException {

            List<AccountMirHistory> mirHistoryList = accountService.getAccountMirHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(mirHistoryList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void mirHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountMirHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountAddresses Tests")
    class GetAccountAddresses {

        @Test
        public void addresses_willReturn_addressesForCountPageAndAscendingOrder() throws APIException {

            List<AccountAddress> addressesList = accountService.getAccountAddresses("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(addressesList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void addresses_willReturn_addressesForCountAndPage() throws APIException {

            List<AccountAddress> addressesList = accountService.getAccountAddresses("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(addressesList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void addresses_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountAddresses(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountAssets Tests")
    class GetAccountAssets {

        @Test
        public void assets_willReturn_assetsForCountPageAndAscendingOrder() throws APIException {

            List<AccountAsset> assetsList = accountService.getAccountAssets("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(assetsList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void assets_willReturn_assetsForCountAndPage() throws APIException {

            List<AccountAsset> assetsList = accountService.getAccountAssets("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(assetsList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void assets_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountAssets(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

}
