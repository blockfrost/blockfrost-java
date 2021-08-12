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

            assertThat(historyList, hasSize(3));
            assertThat(historyList, hasItem(allOf(hasProperty("poolId", is(notNullValue())))));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountHistory> historyList = accountService.getAccountHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(3));
            assertThat(historyList, hasItem(allOf(hasProperty("poolId", is(notNullValue())))));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountRewardHistory Tests")
    class GetAccountRewardHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountRewardHistory> historyList = accountService.getAccountRewardHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountRewardHistory> historyList = accountService.getAccountRewardHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountRewardHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountDelegationHistory Tests")
    class GetAccountDelegationHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountDelegationHistory> historyList = accountService.getAccountDelegationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountDelegationHistory> historyList = accountService.getAccountDelegationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountDelegationHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountRegistrationHistory Tests")
    class GetAccountRegistrationHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountRegistrationHistory> historyList = accountService.getAccountRegistrationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountRegistrationHistory> historyList = accountService.getAccountRegistrationHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountRegistrationHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountWithdrawalHistory Tests")
    class GetAccountWithdrawalHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountWithdrawalHistory> historyList = accountService.getAccountWithdrawalHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountWithdrawalHistory> historyList = accountService.getAccountWithdrawalHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountWithdrawalHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAccountMirHistory Tests")
    class GetAccountMirHistory {

        @Test
        public void history_willReturn_historyForCountPageAndAscendingOrder() throws APIException {

            List<AccountMirHistory> historyList = accountService.getAccountMirHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1, OrderEnum.asc);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void history_willReturn_historyForCountAndPage() throws APIException {

            List<AccountMirHistory> historyList = accountService.getAccountMirHistory("stake_test1upwlsqc3m9629dsf2vw3ycuqv5jhd023xtjh3ax42nvj03gwy2cha", 3, 1);

            assertThat(historyList, hasSize(lessThanOrEqualTo(3)));
        }

        @Test
        public void poolUpdates_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> accountService.getAccountMirHistory(null, 3, 1));
            assertThat(exception.getMessage(), is("Stake address cannot be null or empty"));
        }

    }

}
