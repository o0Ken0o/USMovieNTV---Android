package mobile.kamheisiu.usmovientv.viewmodel.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.SearchRequest;
import mobile.kamheisiu.usmovientv.fragment.search.SearchViewPagerFragment;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class SearchViewPagerFragmentViewModel extends BaseObservable {

    private BehaviorSubject<SearchRequest> searchTxt;
    private BehaviorSubject<GetMoviesList> mGetMoviesList = BehaviorSubject.create();
    private BehaviorSubject<GetTVShowsList> mGetTVShowsList = BehaviorSubject.create();
    private Context mContext;

    public SearchViewPagerFragmentViewModel(BehaviorSubject<SearchRequest> searchTxt, Context context) {
        this.searchTxt = searchTxt;
        mContext = context;
        listenToSearchTxt();
    }

    private void listenToSearchTxt() {
        searchTxt.observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::search);
    }

    private void listenToSearchTxtWithDebounce() {
        searchTxt.debounce(300, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::search);
    }

    private void search(SearchRequest searchRequest) {
        if (searchRequest.getTabIndex() == 0) {
            searchMovies(searchRequest.getKeywords());
        } else {
            searchTVShows(searchRequest.getKeywords());
        }
    }

    private void searchMovies(String keywords) {
        new ApiUtils().getMoviesServices().searchMovies(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMoviesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetMoviesList getMoviesList) {
                        mGetMoviesList.onNext(getMoviesList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void searchTVShows(String keywords) {
        new ApiUtils().getTVShowsServices().searchTVShows(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetTVShowsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetTVShowsList getTVShowsList) {
                        mGetTVShowsList.onNext(getTVShowsList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public BehaviorSubject<GetMoviesList> getGetMoviesList() {
        return mGetMoviesList;
    }

    public BehaviorSubject<GetTVShowsList> getGetTVShowsList() {
        return mGetTVShowsList;
    }

    public void onTabSelected(int position, String keywords) {
        SearchRequest request = new SearchRequest(keywords, position);
        search(request);
    }
}
