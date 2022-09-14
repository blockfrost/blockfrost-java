package io.blockfrost.sdk.impl;

import io.blockfrost.sdk.api.ScriptService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Script;
import io.blockfrost.sdk.api.model.ScriptCbor;
import io.blockfrost.sdk.api.model.ScriptDatumCbor;
import io.blockfrost.sdk.api.model.ScriptDatum;
import io.blockfrost.sdk.api.model.ScriptJson;
import io.blockfrost.sdk.api.model.ScriptRedeemer;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import io.blockfrost.sdk.impl.retrofit.ScriptsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ScriptServiceImpl extends BaseService implements ScriptService {

    ScriptsApi scriptsApi;

    public ScriptServiceImpl(String baseUrl, String projectId) {
        super(baseUrl, projectId);
        scriptsApi = getRetrofit().create(ScriptsApi.class);
    }

    @Override
    public List<Script> getScripts(int count, int page, OrderEnum order) throws APIException {
        ValidationHelper.validateCount(count);

        Call<List<Script>> call = scriptsApi.scriptsGet(getProjectId(), count, page, order.name());

        try {
            Response<List<Script>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching scripts", exp);
        }
    }

    @Override
    public Script getScript(String scriptHash) throws APIException {
        validateHash(scriptHash);

        Call<Script> call = scriptsApi.scriptsScriptHashGet(getProjectId(), scriptHash);

        try {
            Response<Script> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script for hash: " + scriptHash, exp);
        }
    }

    @Override
    public ScriptJson getScriptJson(String scriptHash) throws APIException {
        validateHash(scriptHash);

        Call<ScriptJson> call = scriptsApi.scriptsScriptHashJsonGet(getProjectId(), scriptHash);

        try {
            Response<ScriptJson> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script JSON representation for hash: " + scriptHash, exp);
        }
    }

    @Override
    public ScriptCbor getScriptCbor(String scriptHash) throws APIException {
        validateHash(scriptHash);

        Call<ScriptCbor> call = scriptsApi.scriptsScriptHashCborGet(getProjectId(), scriptHash);

        try {
            Response<ScriptCbor> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script CBOR representation for hash: " + scriptHash, exp);
        }
    }

    @Override
    public List<ScriptRedeemer> getScriptRedeemers(String scriptHash, int count, int page, OrderEnum order) throws APIException {
        ValidationHelper.validateCount(count);
        validateHash(scriptHash);

        Call<List<ScriptRedeemer>> call = scriptsApi.scriptsScriptHashRedeemersGet(getProjectId(), scriptHash, count, page, order.name());

        try {
            Response<List<ScriptRedeemer>> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script redeemers for hash: " + scriptHash, exp);
        }
    }

    @Override
    public ScriptDatum getScriptDatum(String datumHash) throws APIException {
        validateHash(datumHash);

        Call<ScriptDatum> call = scriptsApi.scriptsDatumDatumHashGet(getProjectId(), datumHash);

        try {
            Response<ScriptDatum> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script datum for hash: " + datumHash, exp);
        }
    }

    @Override
    public ScriptDatumCbor getScriptDatumCbor(String datumHash) throws APIException {
        validateHash(datumHash);

        Call<ScriptDatumCbor> call = scriptsApi.scriptsDatumDatumHashCborGet(getProjectId(), datumHash);

        try {
            Response<ScriptDatumCbor> response = call.execute();
            return processResponse(response);
        } catch (IOException exp) {
            throw new APIException("Exception while fetching script datum CBOR representation for hash: " + datumHash, exp);
        }
    }

    private static void validateHash(String hash) throws APIException {
        if (hash == null || hash.trim().isEmpty()) {
            throw new APIException("Hash cannot be null or empty");
        }

        if(!isHexadecimal(hash)) {
            throw new APIException("Hash must be an hexadecimal value");
        }
    }

    private static boolean isHexadecimal(String input) {
        return Pattern.compile("\\p{XDigit}+").matcher(input).matches();
    }
}
