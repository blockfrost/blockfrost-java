package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Address;
import io.blockfrost.sdk.api.model.AddressTotal;
import io.blockfrost.sdk.api.model.AddressTransaction;
import io.blockfrost.sdk.api.model.AddressUtxo;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressServiceTests extends TestBase {

    static AddressService addressService;

    @BeforeAll
    public static void setup() {
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

    @Nested
    @DisplayName("GetAddressUtxos Tests")
    class GetAddressUtxosTests {

        @Test
        public void addressUtxos_willReturn_addressUtxosForCountPageAndAscendingOrder() throws APIException {

            List<AddressUtxo> addressUtxoList = addressService.getAddressUtxos("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg", 2, 1, OrderEnum.asc);

            assertThat(addressUtxoList, hasSize(2));
        }

        @Test
        public void addressUtxos_willReturn_addressUtxosForCountAndPage() throws APIException {

            List<AddressUtxo> addressUtxoList = addressService.getAddressUtxos("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg", 2, 1);

            assertThat(addressUtxoList, hasSize(2));
        }

        @Test
        public void addressUtxos_willReturn_allAddressUtxos() throws APIException {

            List<AddressUtxo> addressUtxoList = addressService.getAllAddressUtxos("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg");

            assertThat(addressUtxoList, hasSize(greaterThanOrEqualTo(0)));
            assertTrue(addressUtxoList.stream().map(AddressUtxo::getDataHash).anyMatch(Objects::nonNull));
            assertTrue(addressUtxoList.stream().map(AddressUtxo::getInlineDatum).anyMatch(Objects::nonNull));
            assertTrue(addressUtxoList.stream().map(AddressUtxo::getReferenceScriptHash).anyMatch(Objects::nonNull));
        }

        @Test
        public void addressUtxos_willThrowAPIException_onNullAddress() {

            Exception exception = assertThrows(APIException.class, () -> addressService.getAddressUtxos("", 2, 1));
            assertThat(exception.getMessage(), is("Address cannot be null or empty"));
        }

    }

    @Nested
    @DisplayName("GetAddressTransactions Tests")
    class GetAddressTransactionsTests {

        @Test
        public void addressTransactions_willReturn_addressTransactionsForCountPageAndAscendingOrderNoRange() throws APIException {

            List<AddressTransaction> addressTransactionListsList = addressService.getAddressTransactions("addr_test1qrlf8mers2lzpp9y278tc4xp8yug7djflrnrr55c8fmg75qxu2hyfhlkwuxupa9d5085eunq2qywy7hvmvej456flkns2gsze6", 100, 1, OrderEnum.asc);

            assertThat(addressTransactionListsList, hasSize(greaterThanOrEqualTo(2)));
        }

        @Test
        public void addressTransactions_willReturn_addressTransactionsForCountPageAndAscendingOrder() throws APIException {


            List<AddressTransaction> addressTransactionListsList = addressService.getAddressTransactions("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg", 2, 1, OrderEnum.asc, "8929261", "9999269");

            assertThat(addressTransactionListsList, hasSize(lessThanOrEqualTo(2)));
        }

        @Test
        public void addressTransactions_willReturn_addressTransactionsForCountAndPage() throws APIException {

            List<AddressTransaction> addressTransactionListsList = addressService.getAddressTransactions("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg", 2, 1, "8929261", "9999269");

            assertThat(addressTransactionListsList, hasSize(lessThanOrEqualTo(2)));
        }

        @Test
        public void addressTransactions_willReturn_allAddressTransactions() throws APIException {

            List<AddressTransaction> addressTransactionListsList = addressService.getAddressTransactions("addr_test1qqy07jaue8tru20877ak7wxpuagrqqpm2pdacfjjtv4z3elcn8dnk52656jspgq03ts2sl6jvefwakdacwfy605m9ydselehdg", "8929261", "9999269");

            assertThat(addressTransactionListsList, hasSize(greaterThanOrEqualTo(0)));
        }

        @Test
        public void addressTransactions_willThrowAPIException_onNullAddress() {

            Exception exception = assertThrows(APIException.class, () -> addressService.getAddressTransactions(null, 2, 1, OrderEnum.asc, "8929261", "9999269"));
            assertThat(exception.getMessage(), is("Address cannot be null or empty"));
        }

    }

}
