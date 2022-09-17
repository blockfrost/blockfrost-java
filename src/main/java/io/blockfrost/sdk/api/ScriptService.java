package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Script;
import io.blockfrost.sdk.api.model.ScriptCbor;
import io.blockfrost.sdk.api.model.ScriptDatumCbor;
import io.blockfrost.sdk.api.model.ScriptDatum;
import io.blockfrost.sdk.api.model.ScriptJson;
import io.blockfrost.sdk.api.model.ScriptRedeemer;
import io.blockfrost.sdk.api.util.OrderEnum;

import java.util.List;

public interface ScriptService {

    /**
     * Scripts
     * List of scripts.
     *
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself.
     *              By default, we return the oldest first, the newest last. (optional, default to "asc")
     * @return List&lt;Script&gt;
     */
    List<Script> getScripts(int count, int page, OrderEnum order) throws APIException;

    /**
     * Specific script
     * Information about a specific script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return Script
     */
    Script getScript(String scriptHash) throws APIException;

    /**
     * Script JSON
     * JSON representation of a timelock script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return ScriptJson
     */
    ScriptJson getScriptJson(String scriptHash) throws APIException;

    /**
     * Script CBOR
     * CBOR representation of a plutus script.
     *
     * @param scriptHash Hash of the script. (required)
     * @return ScriptCbor
     */
    ScriptCbor getScriptCbor(String scriptHash) throws APIException;

    /**
     * Redeemers of a specific script
     * List of redeemers of a specific script.
     *
     * @param scriptHash Hash of the script. (required)
     * @param count The number of results displayed on one page. (optional, default to 100)
     * @param page  The page number for listing the results. (optional, default to 1)
     * @param order The ordering of items from the point of view of the blockchain, not the page listing itself.
     *              By default, we return the oldest first, the newest last. (optional, default to "asc")
     * @return List&lt;ScriptRedeemer&gt;
     */
    List<ScriptRedeemer> getScriptRedeemers(String scriptHash, int count, int page, OrderEnum order) throws APIException;

    /**
     * Datum value
     * Query JSON value of a datum by its hash.
     *
     * @param datumHash Hash of the datum. (required)
     * @return ScriptDatum
     */
    ScriptDatum getScriptDatum(String datumHash) throws APIException;


    /**
     * Datum CBOR value
     * Query CBOR serialised datum by its hash.
     *
     * @param datumHash Hash of the datum. (required)
     * @return ScriptDatumCbor
     */
    ScriptDatumCbor getScriptDatumCbor(String datumHash) throws APIException;
}
