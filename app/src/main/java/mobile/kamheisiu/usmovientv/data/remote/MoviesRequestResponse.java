package mobile.kamheisiu.usmovientv.data.remote;

import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class MoviesRequestResponse {
    boolean isSuccessful;
    boolean isRefresh;
    GetMoviesList mGetMoviesList;
    Throwable mThrowable;

    public MoviesRequestResponse(boolean isSuccessful, GetMoviesList getMoviesList, Throwable throwable, boolean isRefresh) {
        this.isSuccessful = isSuccessful;
        mGetMoviesList = getMoviesList;
        mThrowable = throwable;
        this.isRefresh = isRefresh;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public GetMoviesList getGetMoviesList() {
        return mGetMoviesList;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public boolean isRefresh() {
        return isRefresh;
    }
}
