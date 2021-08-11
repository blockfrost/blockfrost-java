package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressType;
import io.blockfrost.sdk.api.model.TransactionOutputAmount;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class AddressServiceTests extends TestBase {

    AddressService addressService;

    @BeforeEach
    public void setup(){
        addressService = new AddressServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void account_willReturn_accountForBechAddress() throws APIException {

        Address expectedAccount = Address.builder()
                .address("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg")
                .amount(Arrays.asList(
                    TransactionOutputAmount.builder().unit("lovelace").quantity("521276346").build(),
                    TransactionOutputAmount.builder().unit("07a6604234b758be257f26565445f30169c25c85cf392797bc878de753554d").quantity("1200000").build()
                ))
                .stakeAddress("stake_test1urufnkem29d2dfgq5q8c4c9g0afxv5hwmx7u8yjd86djjxcewz60f")
                .type(AddressType.shelley)
                .build();

        Address addressResponse = addressService.getAddress("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg");
        assertThat(addressResponse, samePropertyValuesAs(expectedAccount, "amount"));

    }

}
