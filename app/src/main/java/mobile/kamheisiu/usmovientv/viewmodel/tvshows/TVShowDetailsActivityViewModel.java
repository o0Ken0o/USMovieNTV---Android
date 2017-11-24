package mobile.kamheisiu.usmovientv.viewmodel.tvshows;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.CreatedBy;
import mobile.kamheisiu.usmovientv.data.model.Genre;
import mobile.kamheisiu.usmovientv.data.model.SpokenLanguage;
import mobile.kamheisiu.usmovientv.data.model.TVShowDetails;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.TVShowsServices;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class TVShowDetailsActivityViewModel extends BaseObservable {

    private Context mContext;
    private int mTvShowId;
    private TVShowDetails mTVShowDetails;
    private PublishSubject<Boolean> isDataReady = PublishSubject.create();

    public TVShowDetailsActivityViewModel(Context context, int tvShowId) {
        mContext = context;
        mTvShowId = tvShowId;
        getTVShowDetails();
    }

    private void getTVShowDetails() {
        TVShowsServices tvShowsServices = new ApiUtils().getTVShowsServices();

        tvShowsServices.getTVShowDetails(mTvShowId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TVShowDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TVShowDetails tvShowDetails) {
                        mTVShowDetails = tvShowDetails;
                        isDataReady.onNext(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isDataReady.onNext(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public String getPopularity() {
        return String.format("☆ %.1f", mTVShowDetails.getPopularity());
    }

    public String getVoteAverage() {
        return String.format("♡ %.1f", mTVShowDetails.getVoteAverage());
    }

    public String getVoteCount() {
        return String.format("웃 %d", mTVShowDetails.getVoteCount());
    }

    public String getTitle() {
        return mTVShowDetails.getName();
    }

    public String getGenres() {
        StringBuilder genresBuilder = new StringBuilder();

        List<Genre> genres = mTVShowDetails.getGenres();
        for (int i=0; i<genres.size(); i++) {
            if (i == genres.size() - 1) {
                genresBuilder.append(genres.get(i).getName());
            } else {
                genresBuilder.append(genres.get(i).getName()).append(", ");
            }
        }

        return genresBuilder.toString();
    }

    public String getLanguages() {
        StringBuilder languagesBuilder = new StringBuilder();

        List<String> languages = mTVShowDetails.getLanguages();
        for (int i=0; i<languages.size(); i++) {
            if (i == languages.size() - 1) {
                languagesBuilder.append(languages.get(i));
            } else {
                languagesBuilder.append(languages.get(i)).append(", ");
            }
        }

        return languagesBuilder.toString();
    }

    public String getCreatedBy() {
        StringBuilder createdByListBuilder = new StringBuilder();

        List<CreatedBy> createdBy = mTVShowDetails.getCreatedBy();
        for (int i=0; i<createdBy.size(); i++) {
            if (i == createdBy.size() - 1) {
                createdByListBuilder.append(createdBy.get(i).getName());
            } else {
                createdByListBuilder.append(createdBy.get(i).getName()).append(", ");
            }
        }

        return createdByListBuilder.toString();
    }

    public String getNumberOfSeasons() {
        return mTVShowDetails.getNumberOfSeasons() + "";
    }

    public String getNumberOfEpisodes() {
        return mTVShowDetails.getNumberOfEpisodes() + "";
    }

    public String getRuntime() {
        StringBuilder runtimeBuilder = new StringBuilder();

        List<Integer> runtimes = mTVShowDetails.getEpisodeRunTime();
        for (int i=0; i<runtimes.size(); i++) {
            if (i == runtimes.size() - 1) {
                runtimeBuilder.append(runtimes.get(i));
            } else {
                runtimeBuilder.append(runtimes.get(i)).append(", ");
            }
        }

        return runtimeBuilder.toString();
    }

    public String getOverview() {
        return mTVShowDetails.getOverview();
    }

    public PublishSubject<Boolean> getIsDataReady() {
        return isDataReady;
    }

    public String getPosterPath() {
        return mTVShowDetails.getPosterPath();
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String relativeUrl) {
        if (relativeUrl == null) {
            view.setImageResource(R.drawable.movietv_clear_bg);
            return;
        }

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(ApiUtils.IMAGE_BASE_URL)
                .append(ApiUtils.IMAGE_WIDTH_PATH)
                .append(relativeUrl);

        Glide.with(view.getContext())
                .load(urlBuilder.toString())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.movietv_clear_bg)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(view);
    }
}
