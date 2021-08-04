package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.EpochParam;
import io.blockfrost.sdk.api.model.Stake;

import java.util.List;

public interface EpochService {

    /**
     * Latest epoch
     * Return the information about the latest, therefore current, epoch.
     *
     * @return Epoch
     */
    Epoch getLatestEpoch() throws APIException;

    /**
     * Latest epoch protocol parameters
     * Return the protocol parameters for the latest epoch.
     *
     * @return EpochParam
     */
    EpochParam getLatestEpochParam() throws APIException;

    /**
     * Specific epoch
     * Return the content of the requested epoch.
     *
     * @param number Number of the epoch (required)
     * @return Epoch
     */
    Epoch getEpoch(int number) throws APIException;

    /**
     * Listing of next epochs
     * Return the list of epochs following a specific epoch.
     *
     * @param number Number of the requested epoch. (required)
     * @param count  The number of results displayed on one page. (&lt;=100)
     * @param page   The page number for listing the results.
     * @return List&lt;Epoch&gt;
     */
    List<Epoch> getNextEpochs(int number, int count, int page) throws APIException;

    /**
     * Listing of next epochs
     * Return the list of all epochs following a specific epoch.
     *
     * @param number Number of the requested epoch. (required)
     * @return List&lt;Epoch&gt;
     */
    List<Epoch> getNextEpochs(int number) throws APIException;


    /**
     * Listing of previous epochs
     * Return the list of epochs following a specific epoch.
     *
     * @param number Number of the requested epoch. (required)
     * @param count  The number of results displayed on one page. (&lt;=100)
     * @param page   The page number for listing the results.
     * @return List&lt;Epoch&gt;
     */
    List<Epoch> getPreviousEpochs(int number, int count, int page) throws APIException;

    /**
     * Listing of previous epochs
     * Return the list of all epochs preceding a specific epoch.
     *
     * @param number Number of the requested epoch. (required)
     * @return List&lt;Epoch&gt;
     */
    List<Epoch> getPreviousEpochs(int number) throws APIException;

    /**
     * Stake distribution
     * Return the active stakes for the epoch specified.
     *
     * @param number Number of the epoch (required)
     * @param count  The number of results displayed on one page. (&lt;=100)
     * @param page   The page number for listing the results.
     * @return List&lt;Stake&gt;
     */
    List<Stake> getActiveStakesForEpoch(int number, int count, int page) throws APIException;

    /**
     * Stake distribution
     * Return all active stakes for the epoch specified.
     *
     * @param number Number of the epoch (required)
     * @return List&lt;Stake&gt;
     */
    List<Stake> getActiveStakesForEpoch(int number) throws APIException;
}
