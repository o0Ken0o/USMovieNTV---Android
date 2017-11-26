package mobile.kamheisiu.usmovientv.viewmodel.tvshows;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;
import mobile.kamheisiu.usmovientv.data.remote.TVShowsRequestResponse;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class OnTheAirTVShowsFragmentViewModel extends TVShowsFragmentViewModel {
    @Override
    protected void getTVShows(boolean isRefresh) {
        mTVShowsServices.getOnTheAir().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetTVShowsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetTVShowsList getTVShowsList) {
                        mGetTVShowsList = getTVShowsList;
                        TVShowsRequestResponse requestResponse = new TVShowsRequestResponse(true, isRefresh, getTVShowsList, null);
                        mTVShowsRequestResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleRequestFailure(e);
                        TVShowsRequestResponse requestResponse = new TVShowsRequestResponse(false, isRefresh, null, e);
                        mTVShowsRequestResponse.onNext(requestResponse);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
