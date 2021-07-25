package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Account;
import io.blockfrost.sdk.impl.AccountServiceImpl;
import io.blockfrost.sdk.impl.common.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
