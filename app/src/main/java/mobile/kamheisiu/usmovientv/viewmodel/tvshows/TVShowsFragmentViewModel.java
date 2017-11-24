package mobile.kamheisiu.usmovientv.viewmodel.tvshows;

import android.databinding.BaseObservable;
import android.util.Log;

import java.io.IOException;

import io.reactivex.subjects.PublishSubject;
import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.TVShowsRequestResponse;
import mobile.kamheisiu.usmovientv.data.remote.TVShowsServices;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public abstract class TVShowsFragmentViewModel extends BaseObservable {

    protected TVShowsServices mTVShowsServices;
    protected GetTVShowsList mGetTVShowsList;
    protected PublishSubject<TVShowsRequestResponse> mTVShowsRequestResponse = PublishSubject.create();

    public TVShowsFragmentViewModel() {
        mTVShowsServices = new ApiUtils().getTVShowsServices();
        getTVShows(false);
    }

    public PublishSubject<TVShowsRequestResponse> getTVShowsRequestResponse() {
        return mTVShowsRequestResponse;
    }

    public GetTVShowsList getCurrentTVShowsList() {
        return mGetTVShowsList;
    }

    public void onRefresh() {
        getTVShows(true);
    }

    protected abstract void getTVShows(boolean isRefresh);

    protected void handleRequestFailure(Throwable t) {
        // When the Throwable passed to the failure callback is an IOException,
        // this means that it was a network problem (socket timeout, unknown host, etc.).
        // Any other exception means something broke either in serializing/deserializing the data
        // or it's a configuration problem
        if (t instanceof IOException) {
            handleRequestNetworkError(t);
        } else {
            handleRequestNonNetworkError(t);
        }
    }

    protected void handleRequestNetworkError(Throwable t) {
        Log.d("debug3", "handleRequestNetworkError: " + t.getMessage());
    }

    protected void handleRequestNonNetworkError(Throwable t) {
        Log.d("debug3", "handleRequestNonNetworkError: " + t.getMessage());
    }
}
