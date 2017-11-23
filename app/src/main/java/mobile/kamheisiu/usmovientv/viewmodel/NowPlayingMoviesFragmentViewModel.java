package mobile.kamheisiu.usmovientv.viewmodel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.remote.MoviesRequestResponse;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class NowPlayingMoviesFragmentViewModel extends MoviesFragmentViewModel {
    @Override
    protected void getMovies(boolean isRefresh) {
        moviesServices.getNowPlaying().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMoviesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetMoviesList getMoviesList) {
                        mGetMoviesList = getMoviesList;
                        MoviesRequestResponse requestResponse = new MoviesRequestResponse(true, getMoviesList, null, isRefresh);
                        mGetMoviesResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleRequestFailure(e);
                        MoviesRequestResponse requestResponse = new MoviesRequestResponse(false, null, e, isRefresh);
                        mGetMoviesResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
