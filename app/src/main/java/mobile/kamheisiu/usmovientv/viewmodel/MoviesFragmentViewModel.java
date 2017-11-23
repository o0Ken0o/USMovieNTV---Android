package mobile.kamheisiu.usmovientv.viewmodel;

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
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;

/**
 * Created by kamheisiu on 22/11/2017.
 */

public class MoviesFragmentViewModel extends BaseObservable {

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

    private MoviesServices moviesServices;
    private GetMoviesList mGetMoviesList;
    private PublishSubject<MoviesRequestResponse> mGetMoviesResponse = PublishSubject.create();

    public MoviesFragmentViewModel() {
        moviesServices = new ApiUtils().getMoviesServices();
        getNowPlayingMovies(false);
    }

    public PublishSubject<MoviesRequestResponse> getGetMoviesResponse() {
        return mGetMoviesResponse;
    }

    public GetMoviesList getCurrentGetMoviesList() {
        return mGetMoviesList;
    }

    public void onRefresh() {
        getNowPlayingMovies(true);
    }

    private void getNowPlayingMovies(boolean isRefresh) {
        moviesServices.getNowPlaying().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMoviesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetMoviesList getMoviesList) {
                        mGetMoviesList = getMoviesList;
                        MoviesRequestResponse requestResponse = new MoviesRequestResponse(true , getMoviesList, null, isRefresh);
                        mGetMoviesResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleRequestFailure(e);
                        MoviesRequestResponse requestResponse = new MoviesRequestResponse(false , null, e, isRefresh);
                        mGetMoviesResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void handleRequestFailure(Throwable t) {
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

    private void handleRequestNetworkError(Throwable t) {
        Log.d("debug3", "handleRequestNetworkError: " + t.getMessage());
    }

    private void handleRequestNonNetworkError(Throwable t) {
        Log.d("debug3", "handleRequestNonNetworkError: " + t.getMessage());
    }
}
