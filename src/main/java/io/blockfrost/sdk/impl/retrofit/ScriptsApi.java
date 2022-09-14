package io.blockfrost.sdk.impl.retrofit;

import io.blockfrost.sdk.api.model.Script;
import io.blockfrost.sdk.api.model.ScriptCbor;
import io.blockfrost.sdk.api.model.ScriptDatumCbor;
import io.blockfrost.sdk.api.model.ScriptDatum;
import io.blockfrost.sdk.api.model.ScriptJson;
import io.blockfrost.sdk.api.model.ScriptRedeemer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ScriptsApi {

    /**
     * Scripts
     * List of scripts.
     *
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself.
     *              By default, we return the oldest first, the newest last. (optional, default to "asc")
     * @return Call&lt;List&lt;Script&gt;&gt;
     */
    @GET("scripts")
    Call<List<Script>> scriptsGet(
            @Header("project_id") String projectId,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Specific script
     * Information about a specific script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return Call&lt;Script&gt;
     */
    @GET("scripts/{script_hash}")
    Call<Script> scriptsScriptHashGet(
            @Header("project_id") String projectId,
            @Path("script_hash") String scriptHash
    );

    /**
     * Script JSON
     * JSON representation of a timelock script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return Call&lt;ScriptJson&gt;
     */
    @GET("scripts/{script_hash}/json")
    Call<ScriptJson> scriptsScriptHashJsonGet(
            @Header("project_id") String projectId,
            @Path("script_hash") String scriptHash
    );

    /**
     * Script CBOR
     * CBOR representation of a plutus script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return Call&lt;ScriptCbor&gt;
     */
    @GET("scripts/{script_hash}/cbor")
    Call<ScriptCbor> scriptsScriptHashCborGet(
            @Header("project_id") String projectId,
            @Path("script_hash") String scriptHash
    );

    /**
     * Redeemers of a specific script
     * List of redeemers of a specific script.
     *
     * @param scriptHash Hash of the script. (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself.
     *              By default, we return the oldest first, the newest last. (optional, default to "asc")
     * @return Call&lt;List&lt;ScriptRedeemer&gt;&gt;
     */
    @GET("scripts/{script_hash}/redeemers")
    Call<List<ScriptRedeemer>> scriptsScriptHashRedeemersGet(
            @Header("project_id") String projectId,
            @Path("script_hash") String scriptHash,
            @Query("count") Integer count,
            @Query("page") Integer page,
            @Query("order") String order
    );

    /**
     * Datum value
     * Query JSON value of a datum by its hash
     *
     * @param datumHash Hash of the datum. (required)
     * @return Call&lt;ScriptDatum&gt;
     */
    @GET("scripts/datum/{datum_hash}")
    Call<ScriptDatum> scriptsDatumDatumHashGet(
            @Header("project_id") String projectId,
            @Path("datum_hash") String datumHash
    );


    /**
     * Datum CBOR value
     * Query CBOR serialised datum by its hash
     *
     * @param datumHash Hash of the datum. (required)
     * @return Call&lt;ScriptDatumCbor&gt;
     */
    @GET("scripts/datum/{datum_hash}/cbor")
    Call<ScriptDatumCbor> scriptsDatumDatumHashCborGet(
            @Header("project_id") String projectId,
            @Path("datum_hash") String datumHash
    );

}
