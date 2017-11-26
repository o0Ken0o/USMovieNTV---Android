package mobile.kamheisiu.usmovientv.viewmodel.movies;

import android.databinding.BaseObservable;
import android.util.Log;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.MoviesRequestResponse;
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;

/**
 * Created by kamheisiu on 22/11/2017.
 */

public abstract class MoviesFragmentViewModel extends BaseObservable {

    protected MoviesServices moviesServices;
    protected GetMoviesList mGetMoviesList;
    protected PublishSubject<MoviesRequestResponse> mGetMoviesResponse = PublishSubject.create();

    public MoviesFragmentViewModel() {
        moviesServices = new ApiUtils().getMoviesServices();
        getMovies(false);
    }

    public PublishSubject<MoviesRequestResponse> getGetMoviesResponse() {
        return mGetMoviesResponse;
    }

    public GetMoviesList getCurrentGetMoviesList() {
        return mGetMoviesList;
    }

    public void onRefresh() {
        getMovies(true);
    }

    protected abstract void getMovies(boolean isRefresh);

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
