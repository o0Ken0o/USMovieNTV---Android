package mobile.kamheisiu.usmovientv.data.remote;

import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TVShowsRequestResponse {
    boolean isSuccessful;
    boolean isRefresh;
    GetTVShowsList mGetTVShowsList;
    Throwable mThrowable;

    public TVShowsRequestResponse(boolean isSuccessful, boolean isRefresh, GetTVShowsList getTVShowsList, Throwable throwable) {
        this.isSuccessful = isSuccessful;
        this.isRefresh = isRefresh;
        mGetTVShowsList = getTVShowsList;
        mThrowable = throwable;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public GetTVShowsList getGetTVShowsList() {
        return mGetTVShowsList;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
