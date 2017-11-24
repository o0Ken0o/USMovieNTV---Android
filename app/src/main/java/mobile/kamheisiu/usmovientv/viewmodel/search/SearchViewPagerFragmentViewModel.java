package mobile.kamheisiu.usmovientv.viewmodel.search;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class SearchViewPagerFragmentViewModel extends BaseObservable {

    private BehaviorSubject<String> searchTxt;
    private Context mContext;

    public SearchViewPagerFragmentViewModel(BehaviorSubject<String> searchTxt, Context context) {
        this.searchTxt = searchTxt;
        mContext = context;
        listenToSearchTxt();
    }

    private void listenToSearchTxt() {
        searchTxt.debounce(300, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::search);
    }

    private void search(String keywords) {
        Log.d("debug3", "search: " + keywords);
    }
}
