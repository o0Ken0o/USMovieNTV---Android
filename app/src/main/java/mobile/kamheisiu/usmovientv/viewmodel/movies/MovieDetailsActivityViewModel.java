package mobile.kamheisiu.usmovientv.viewmodel.movies;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.activity.MovieDetailsActivity;
import mobile.kamheisiu.usmovientv.data.model.Genre;
import mobile.kamheisiu.usmovientv.data.model.Movie;
import mobile.kamheisiu.usmovientv.data.model.MovieDetails;
import mobile.kamheisiu.usmovientv.data.model.ProductionCompany;
import mobile.kamheisiu.usmovientv.data.model.ProductionCountry;
import mobile.kamheisiu.usmovientv.data.model.SpokenLanguage;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;

/**
 * Created by kamheisiu on 21/11/2017.
 */

public class MovieDetailsActivityViewModel extends BaseObservable {

    private Context mContext;
    private int mMovieId;
    private MovieDetails mMovieDetails;
    private PublishSubject<Boolean> isDataReady = PublishSubject.create();

    public MovieDetailsActivityViewModel(Context context, int movieId) {
        mContext = context;
        mMovieId = movieId;
        getMovieDetails();
    }

    private void getMovieDetails() {
        MoviesServices moviesServices = new ApiUtils().getMoviesServices();

        moviesServices.getMovieDetails(mMovieId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        mMovieDetails = movieDetails;
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

    public PublishSubject<Boolean> getIsDataReady() {
        return isDataReady;
    }

    public String getPopularity() {
        return String.format("☆ %.1f", mMovieDetails.getPopularity());
    }

    public String getVoteAverage() {
        return String.format("♡ %.1f", mMovieDetails.getVoteAverage());
    }

    public String getVoteCount() {
        return String.format("웃 %d", mMovieDetails.getVoteCount());
    }

    public String getTitle() {
        return mMovieDetails.getTitle();
    }

    public String getGenres() {
        StringBuilder genresBuilder = new StringBuilder();

        List<Genre> genres = mMovieDetails.getGenres();
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

        List<SpokenLanguage> languages = mMovieDetails.getSpokenLanguages();
        for (int i=0; i<languages.size(); i++) {
            if (i == languages.size() - 1) {
                languagesBuilder.append(languages.get(i).getName());
            } else {
                languagesBuilder.append(languages.get(i).getName()).append(", ");
            }
        }

        return languagesBuilder.toString();
    }

    public String getCompanies() {
        StringBuilder companiesBuilder = new StringBuilder();

        List<ProductionCompany> companies = mMovieDetails.getProductionCompanies();
        for (int i=0; i<companies.size(); i++) {
            if (i == companies.size() - 1) {
                companiesBuilder.append(companies.get(i).getName());
            } else {
                companiesBuilder.append(companies.get(i).getName()).append(", ");
            }
        }

        return companiesBuilder.toString();
    }

    public String getCountries() {
        StringBuilder countriesBuilder = new StringBuilder();

        List<ProductionCountry> countries = mMovieDetails.getProductionCountries();
        for (int i=0; i<countries.size(); i++) {
            if (i == countries.size() - 1) {
                countriesBuilder.append(countries.get(i).getName());
            } else {
                countriesBuilder.append(countries.get(i).getName()).append(", ");
            }
        }

        return countriesBuilder.toString();
    }

    public String getReleaseDate() {
        return mMovieDetails.getReleaseDate();
    }

    public String getRuntime() {
        return mMovieDetails.getRuntime() + "";
    }

    public String getPosterPath() {
        return mMovieDetails.getPosterPath();
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
