package io.blockfrost.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Script;
import io.blockfrost.sdk.api.model.ScriptCbor;
import io.blockfrost.sdk.api.model.ScriptDatum;
import io.blockfrost.sdk.api.model.ScriptDatumCbor;
import io.blockfrost.sdk.api.model.ScriptJson;
import io.blockfrost.sdk.api.model.ScriptRedeemer;
import io.blockfrost.sdk.api.model.ScriptType;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.ScriptServiceImpl;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScriptServiceTests extends TestBase {

    private static final String PLUTUS_SCRIPT_HASH = "67f33146617a5e61936081db3b2117cbf59bd2123748f58ac9678656";
    private static final String TIMELOCK_SCRIPT_HASH = "69b30e43bc5401bb34d0b12bd06cd9b537f33065aa49df7e8652739d";

    private static ScriptService scriptService;

    @BeforeAll
    public static void setup() {
        scriptService = new ScriptServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Nested
    @DisplayName("GetScripts Tests")
    class GetScriptsTests {

        @Test
        public void getScripts_willReturn_scriptsForCountPageAndAscendingOrder() throws APIException {
            List<String> expectedScriptHashes = Arrays.asList(
                    "df22511a7957d47745f5019c2d9f8636cf465afc9e014228c39c708d",
                    "c2287941693f27466b90eee087b6a0c3a28ecb7151579b554b79e481",
                    "de1cf53eb63dde7c94c5689bcc6d7f4472da400d6e100fc67460cf84",
                    "476039a0949cf0b22f6a800f56780184c44533887ca6e821007840c3",
                    "69b30e43bc5401bb34d0b12bd06cd9b537f33065aa49df7e8652739d"
            );

            List<Script> scriptList = scriptService.getScripts(5, 1, OrderEnum.asc);

            assertThat(scriptList, hasSize(5));
            assertThat(scriptList.stream().map(Script::getScriptHash).allMatch(expectedScriptHashes::contains), is(true));
        }

        @Test
        public void getScripts_willThrowAPIException_onCountGreaterThan100() {
            Exception exception = assertThrows(APIException.class, () -> scriptService.getScripts(101, 1, OrderEnum.asc));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));
        }

    }

    @Nested
    @DisplayName("GetScript Tests")
    class GetScriptTests {

        @Test
        public void getScript_willReturn_scriptForTimelockScriptHash() throws APIException {
            Script expectedScript = Script.builder()
                    .scriptHash(TIMELOCK_SCRIPT_HASH)
                    .type(ScriptType.timelock)
                    .serialisedSize(null)
                    .build();

            Script script = scriptService.getScript(expectedScript.getScriptHash());

            assertThat(script, is(notNullValue()));
            assertThat(script.getScriptHash(), is(expectedScript.getScriptHash()));
            assertThat(script.getType(), is(expectedScript.getType()));
            assertThat(script.getSerialisedSize(), is(expectedScript.getSerialisedSize()));
        }

        @Test
        public void getScript_willReturn_scriptForPlutusScriptHash() throws APIException {
            Script expectedScript = Script.builder()
                    .scriptHash(PLUTUS_SCRIPT_HASH)
                    .type(ScriptType.plutusV1)
                    .serialisedSize(14)
                    .build();

            Script script = scriptService.getScript(expectedScript.getScriptHash());

            assertThat(script, is(notNullValue()));
            assertThat(script.getScriptHash(), is(expectedScript.getScriptHash()));
            assertThat(script.getType(), is(expectedScript.getType()));
            assertThat(script.getSerialisedSize(), is(expectedScript.getSerialisedSize()));
        }

        @ParameterizedTest(name = "getScript will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScript_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScript(hash));
        }
    }

    @Nested
    @DisplayName("GetScriptJson Tests")
    class GetScriptJsonTests {

        @Test
        public void getScriptJson_willReturn_jsonForTimelockScriptHash() throws APIException {
            ScriptJson scriptJson = scriptService.getScriptJson(TIMELOCK_SCRIPT_HASH);

            Map<String, Object> jsonAsMap = new ObjectMapper().convertValue(scriptJson.getJson(), new TypeReference<>(){});
            assertThat(jsonAsMap.get("type"), is("all"));
            List<Object> scripts = (List)jsonAsMap.get("scripts");
            assertThat(scripts, hasSize(2));
        }

        @Test
        public void getScriptJson_willReturn_nullForPlutusScriptHash() throws APIException {
            ScriptJson json = scriptService.getScriptJson(PLUTUS_SCRIPT_HASH);

            assertThat(json.getJson().isNull(), is(true));
        }

        @ParameterizedTest(name = "getScriptJson will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScriptJson_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScriptJson(hash));
        }
    }

    @Nested
    @DisplayName("GetScriptCbor Tests")
    class GetScriptCborTests {

        @Test
        public void getScriptCbor_willReturn_cborForPlutusScriptHash() throws APIException {
            ScriptCbor cbor = scriptService.getScriptCbor(PLUTUS_SCRIPT_HASH);

            assertThat(cbor.getCbor(), is("4e4d01000033222220051200120011"));
        }

        @Test
        public void getScriptCbor_willReturn_nullCborForTimelockScriptHash() throws APIException {
            ScriptCbor cbor = scriptService.getScriptCbor(TIMELOCK_SCRIPT_HASH);

            assertThat(cbor.getCbor(), nullValue());
        }

        @ParameterizedTest(name = "getScriptCbor will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScriptCbor_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScriptCbor(hash));
        }

    }

    @Nested
    @DisplayName("GetScriptRedeemers Tests")
    class GetScriptRedeemersTests {

        @Test
        public void getScriptRedeemers_willReturn_redeemersForCountPageAndAscendingOrder() throws APIException {
            List<ScriptRedeemer> redeemers = scriptService.getScriptRedeemers(PLUTUS_SCRIPT_HASH, 5, 1, OrderEnum.asc);

            assertThat(redeemers, hasSize(5));
            ScriptRedeemer fifthRedeemer = redeemers.get(4);
            assertThat(fifthRedeemer.getTxHash(), is("3543502f973c3daf53febbb63fb1207c0b74293e476a3773b402a6cc17d1b8bf"));
            assertThat(fifthRedeemer.getTxIndex(), is(0));
            assertThat(fifthRedeemer.getPurpose(), is("spend"));
            assertThat(fifthRedeemer.getRedeemerDataHash(), is("fcaa61fb85676101d9e3398a484674e71c45c3fd41b492682f3b0054f4cf3273"));
            assertThat(fifthRedeemer.getDatumHash(), is("fcaa61fb85676101d9e3398a484674e71c45c3fd41b492682f3b0054f4cf3273"));
            assertThat(fifthRedeemer.getUnitMem(), is("1700"));
            assertThat(fifthRedeemer.getUnitSteps(), is("476468"));
            assertThat(fifthRedeemer.getFee(), is("133"));
        }

        @Test
        public void getScriptRedeemers_willThrowAPIException_onCountGreaterThan100() {
            Exception exception = assertThrows(
                    APIException.class,
                    () -> scriptService.getScriptRedeemers(PLUTUS_SCRIPT_HASH, 101, 1, OrderEnum.asc)
            );
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));
        }

        @ParameterizedTest(name = "getScriptRedeemers will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScriptRedeemers_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScriptRedeemers(hash, 5, 1, OrderEnum.asc));
        }

    }

    @Nested
    @DisplayName("GetScriptDatum Tests")
    class GetScriptDatumTests {

        @Test
        public void getScriptDatum_willReturn_datumForDatumHash() throws APIException {
            ScriptDatum datum = scriptService.getScriptDatum("db583ad85881a96c73fbb26ab9e24d1120bb38f45385664bb9c797a2ea8d9a2d");

            assertThat(datum.getJsonValue().get("int").asInt(), is(314));
        }

        @ParameterizedTest(name = "getScriptDatum will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScriptDatum_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScriptDatum(hash));
        }

    }

    @Nested
    @DisplayName("GetScriptDatumCbor Tests")
    class GetScriptDatumCborTests {

        @Test
        public void getScriptDatumCbor_willReturn_cborForDatumHash() throws APIException {
            ScriptDatumCbor cbor = scriptService.getScriptDatumCbor("db583ad85881a96c73fbb26ab9e24d1120bb38f45385664bb9c797a2ea8d9a2d");

            assertThat(cbor.getCbor(), is("19013a"));
        }

        @ParameterizedTest(name = "getScriptDatumCbor will throw APIException for script hash ''{0}''")
        @NullSource
        @ValueSource(strings = {"", " ", "notHex"})
        public void getScriptDatumCbor_willThrowAPIException_onInvalidScriptHash(String hash) {
            assertThrows(APIException.class, () -> scriptService.getScriptDatumCbor(hash));
        }

    }

}
