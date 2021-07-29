package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.*;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.PoolServiceImpl;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PoolServiceTests extends TestBase {

    PoolService poolService;

    @BeforeEach
    public void setup(){
        poolService = new PoolServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }

    @Nested
    @DisplayName("GetPool Tests")
    class GetPoolTests {
        @Test
        public void pool_willReturn_poolForPoolId() throws APIException {

            Pool expectedPool = Pool.builder()
                    .poolId("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem")
                    .hex("5685f37bca393c683cf03e428280312c6c4ea485188672a2a0b3195c")
                    .vrfKey("3409a1bebeaa47e6d99e0748a99f65dee60b7f7e9a64dc865d52b4fb445b98ab")
                    .blocksMinted(0)
                    .liveStake("997443657")
                    .liveSize(new BigDecimal("6.98550757958531E-8"))
                    .liveSaturation(new BigDecimal("0.00001780488133619636"))
                    .liveDelegators(new BigDecimal("1"))
                    .activeStake("0")
                    .activeSize(new BigDecimal("0"))
                    .declaredPledge("10000000")
                    .livePledge("997443657")
                    .marginCost(new BigDecimal("0.075"))
                    .fixedCost("340000000")
                    .rewardAccount("stake_test1up32f2hrv5ytqk8ad6e4apss5zrrjjlrkjhrksypn5g08fqrqf9gr")
                    .owners(Collections.singletonList("stake_test1up32f2hrv5ytqk8ad6e4apss5zrrjjlrkjhrksypn5g08fqrqf9gr"))
                    .registration(Collections.singletonList("78925fad4cce75a22a675ed5e175ecfd40baf7ac51c487c5cdb0fde9a02afa64"))
                    .retirement(Collections.singletonList("fd8a94eaa104d73b177ff092f959c9ae376bd9fb464a57cfc85664c4823011ed"))
                    .build();

            Pool pool = poolService.getPool("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem");
            assertThat(pool, is(notNullValue()));
            assertThat(pool, samePropertyValuesAs(expectedPool, "liveSaturation", "liveSize"));

        }

        @Test
        public void pool_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPool(null));
            assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));
        }
    }

    @Nested
    @DisplayName("GetPools Tests")
    class GetPoolsTests {

        @Test
        public void pools_willReturn_poolsForCountPageAndAscendingOrder() throws APIException {

            String[] expectedPoolList = {
                    "pool1adur9jcn0dkjpm3v8ayf94yn3fe5xfk2rqfz7rfpuh6cw6evd7w",
                    "pool18kd2k7kqt9gje9y0azahww4dqak9azeeg8ayl0xl7dzewg70vlf",
                    "pool13dgxp4ph2ut5datuh5na4wy7hrnqgkj4fyvac3e8fzfqcc7qh0h",
                    "pool1wnf793xkgrw3s800tfdkkg3s3ddgxkucenahzs7490g4q0cpe0v",
                    "pool156gxlrk0e3phxadasa33yzk9e94wg7tv3au02jge8eanv9zc4ym"
            };

            List<String> poolList = poolService.getPools(5, 1, OrderEnum.asc);

            assertThat(poolList, hasSize(5));
            assertThat(poolList, contains(expectedPoolList));

        }

        @Test
        public void pools_willReturn_poolsForCountPage() throws APIException {

            String[] expectedPoolList = {
                    "pool1adur9jcn0dkjpm3v8ayf94yn3fe5xfk2rqfz7rfpuh6cw6evd7w",
                    "pool18kd2k7kqt9gje9y0azahww4dqak9azeeg8ayl0xl7dzewg70vlf",
                    "pool13dgxp4ph2ut5datuh5na4wy7hrnqgkj4fyvac3e8fzfqcc7qh0h",
                    "pool1wnf793xkgrw3s800tfdkkg3s3ddgxkucenahzs7490g4q0cpe0v",
                    "pool156gxlrk0e3phxadasa33yzk9e94wg7tv3au02jge8eanv9zc4ym"
            };

            List<String> poolList = poolService.getPools(5, 1);

            assertThat(poolList, hasSize(5));
            assertThat(poolList, contains(expectedPoolList));

        }

        @Test
        public void pools_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPools(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }

    @Nested
    @DisplayName("GetRetiredPools Tests")
    class GetRetiredPoolsTests {

        @Test
        public void retiredPools_willReturn_retiredPoolsForCountPageAndAscendingOrder() throws APIException {

            List<PoolRetirementInfo> poolRetirementInfoList = Arrays.asList(
                    PoolRetirementInfo.builder().poolId("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem").epoch(76).build(),
                    PoolRetirementInfo.builder().poolId("pool16kc6ck4clmhg2aykwhkymnz2ypk54yuvk0txt3p6mrw05hrsj3a").epoch(76).build(),
                    PoolRetirementInfo.builder().poolId("pool1y25deq9kldy9y9gfvrpw8zt05zsrfx84zjhugaxrx9ftvwdpua2").epoch(78).build()
            );

            List<PoolRetirementInfo> poolList = poolService.getRetiredPools(3, 1, OrderEnum.asc);

            assertThat(poolList, hasSize(3));
            assertThat(poolList, contains(poolRetirementInfoList.toArray()));
        }

        @Test
        public void retiredPools_willReturn_retiredPoolsForCountPage() throws APIException {

            List<PoolRetirementInfo> poolRetirementInfoList = Arrays.asList(
                    PoolRetirementInfo.builder().poolId("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem").epoch(76).build(),
                    PoolRetirementInfo.builder().poolId("pool16kc6ck4clmhg2aykwhkymnz2ypk54yuvk0txt3p6mrw05hrsj3a").epoch(76).build(),
                    PoolRetirementInfo.builder().poolId("pool1y25deq9kldy9y9gfvrpw8zt05zsrfx84zjhugaxrx9ftvwdpua2").epoch(78).build()
            );

            List<PoolRetirementInfo> poolList = poolService.getRetiredPools(3, 1);

            assertThat(poolList, hasSize(3));
            assertThat(poolList, contains(poolRetirementInfoList.toArray()));
        }

        @Test
        public void retiredPools_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getRetiredPools(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }

    @Nested
    @DisplayName("GetRetiringPools Tests")
    class GetRetiringPoolsTests {

        @Test
        public void retiringPools_willReturn_retiringPoolsForCountPageAndAscendingOrder() throws APIException {

            List<PoolRetirementInfo> poolRetirementInfoList = Arrays.asList(
                    PoolRetirementInfo.builder().poolId("pool1w660hycvxgc2k5ac6ff6jrn9dp3ycr9zrf26wvjchfctvp5f39v").epoch(148).build()
            );

            List<PoolRetirementInfo> poolList = poolService.getRetiringPools(1, 1, OrderEnum.asc);

            assertThat(poolList, hasSize(1));
            assertThat(poolList, contains(poolRetirementInfoList.toArray()));
        }

        @Test
        public void retiringPools_willReturn_retiringPoolsForCountPage() throws APIException {

            List<PoolRetirementInfo> poolRetirementInfoList = Arrays.asList(
                    PoolRetirementInfo.builder().poolId("pool1w660hycvxgc2k5ac6ff6jrn9dp3ycr9zrf26wvjchfctvp5f39v").epoch(148).build()
            );

            List<PoolRetirementInfo> poolList = poolService.getRetiringPools(1, 1);

            assertThat(poolList, hasSize(1));
            assertThat(poolList, contains(poolRetirementInfoList.toArray()));
        }

        @Test
        public void retiringPools_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getRetiringPools(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }


    @Nested
    @DisplayName("GetPoolHistory Tests")
    class GetPoolHistoryTests {

        @Test
        public void poolHistory_willReturn_poolHistoryForCountPageAndAscendingOrder() throws APIException {

            List<PoolHistory> expectedPoolHistory = Arrays.asList(
                    PoolHistory.builder()
                            .epoch(77)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.00016613871489641306"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build(),
                    PoolHistory.builder()
                            .epoch(78)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.000014972989086644468"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build(),
                    PoolHistory.builder()
                            .epoch(79)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.000002942120178511434"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build()
            );

            List<PoolHistory> poolHistory = poolService.getPoolHistory("pool1adur9jcn0dkjpm3v8ayf94yn3fe5xfk2rqfz7rfpuh6cw6evd7w", 3, 1, OrderEnum.asc);

            assertThat(poolHistory, hasSize(3));
            assertThat(poolHistory, contains(expectedPoolHistory.toArray()));
        }

        @Test
        public void poolHistory_willReturn_poolHistoryForCountPage() throws APIException {

            List<PoolHistory> expectedPoolHistory = Arrays.asList(
                    PoolHistory.builder()
                            .epoch(77)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.00016613871489641306"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build(),
                    PoolHistory.builder()
                            .epoch(78)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.000014972989086644468"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build(),
                    PoolHistory.builder()
                            .epoch(79)
                            .blocks(0)
                            .activeStake("1497626626")
                            .activeSize(new BigDecimal("0.000002942120178511434"))
                            .delegatorsCount(1)
                            .rewards("0")
                            .fees("0")
                            .build()
            );


            List<PoolHistory> poolHistory = poolService.getPoolHistory("pool1adur9jcn0dkjpm3v8ayf94yn3fe5xfk2rqfz7rfpuh6cw6evd7w", 3, 1);

            assertThat(poolHistory, hasSize(3));
            assertThat(poolHistory, contains(expectedPoolHistory.toArray()));
        }

        @Test
        public void poolHistory_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolHistory("pool1adur9jcn0dkjpm3v8ayf94yn3fe5xfk2rqfz7rfpuh6cw6evd7w", 101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

        @Test
        public void poolHistory_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolHistory(null, 101, 1));
            assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));

        }

    }

    @Nested
    @DisplayName("GetPoolMetadata Tests")
    class GetPoolMetadataTests {
        @Test
        public void poolMetadata_willReturn_poolMetadataForPoolId() throws APIException {

            PoolMetadata expectedPoolMetadata = PoolMetadata.builder()
                    .poolId("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem")
                    .hex("5685f37bca393c683cf03e428280312c6c4ea485188672a2a0b3195c")
                    .url("https://visionstaking.ch/poolmeta.json")
                    .hash("7feb5bf22fc8c57be71a4b24f68381a7d1051e94290164530da6f7d5682a0024")
                    .ticker("visn")
                    .name("vision")
                    .description("Vision Staking")
                    .homepage("https://visionstaking.ch")
                    .build();

            PoolMetadata poolMetadata = poolService.getPoolMetadata("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem");
            assertThat(poolMetadata, is(notNullValue()));
            assertThat(poolMetadata, is(expectedPoolMetadata));

        }

        @Test
        public void pool_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolMetadata(null));
            assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));
        }
    }

    @Nested
    @DisplayName("GetPoolRelays Tests")
    class GetPoolRelaysTests {
        @Test
        public void poolRelays_willReturn_poolRelaysForPoolId() throws APIException {

            List<PoolRelay> expectedPoolRelays = Arrays.asList(
                    PoolRelay.builder()
                            .ipv4("120.12.13.43")
                            .ipv6(null)
                            .dns(null)
                            .dnsSrv(null)
                            .port(6000)
                            .build()
            );

            List<PoolRelay> poolRelays = poolService.getPoolRelays("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem");
            assertThat(poolRelays, hasSize(1));
            assertThat(poolRelays, is(expectedPoolRelays));

        }

        @Test
        public void pool_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolRelays(null));
            assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));
        }
    }

    @Nested
    @DisplayName("GetPoolDelegators Tests")
    class GetPoolDelegatorsTests {

        @Test
        public void poolDelegators_willReturn_poolDelegatorsForCountPageAndAscendingOrder() throws APIException {

            List<PoolDelegator> expectedPoolDelegatorList = Arrays.asList(
                    PoolDelegator.builder()
                            .address("stake_test1up32f2hrv5ytqk8ad6e4apss5zrrjjlrkjhrksypn5g08fqrqf9gr")
                            .liveStake("997443657")
                            .build()
            );

            List<PoolDelegator> poolDelegatorList = poolService.getPoolDelegators("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem", 3, 1, OrderEnum.asc);

            assertThat(poolDelegatorList, hasSize(1));
            assertThat(poolDelegatorList, contains(expectedPoolDelegatorList.toArray()));
        }

        @Test
        public void poolDelegators_willReturn_poolDelegatorsForCountPage() throws APIException {

            List<PoolDelegator> expectedPoolDelegatorList = Arrays.asList(
                    PoolDelegator.builder()
                            .address("stake_test1up32f2hrv5ytqk8ad6e4apss5zrrjjlrkjhrksypn5g08fqrqf9gr")
                            .liveStake("997443657")
                            .build()
            );

            List<PoolDelegator> poolDelegatorList = poolService.getPoolDelegators("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem", 3, 1, OrderEnum.asc);

            assertThat(poolDelegatorList, hasSize(1));
            assertThat(poolDelegatorList, contains(expectedPoolDelegatorList.toArray()));

        }

        @Test
        public void poolDelegators_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolDelegators("pool126zlx7728y7xs08s8epg9qp393kyafy9rzr89g4qkvv4cv93zem", 101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

        @Test
        public void poolDelegators_willThrowAPIException_onNullPoolId() {

            Exception exception = assertThrows(APIException.class, () -> poolService.getPoolRelays(null));
            assertThat(exception.getMessage(), is("PoolId cannot be null or empty"));
        }



    }

}
