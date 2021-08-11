package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressServiceTests extends TestBase {

    AddressService addressService;

    @BeforeEach
    public void setup(){
        addressService = new AddressServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Test
    public void address_willReturn_addressForBechAddress() throws APIException {

        Address addressResponse = addressService.getAddress("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg");
        assertThat(addressResponse.getAddress(), is("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg"));

    }

    //ToDo: Is this enough or should we create a temp address and test all params
    @Test
    public void addressDetails_willReturn_addressDetailsForBechAddress() throws APIException {

        AddressTotal addressTotalResponse = addressService.getAddressDetails("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg");
        assertThat(addressTotalResponse.getAddress(), is("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg"));

    }

}
