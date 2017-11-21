package mobile.kamheisiu.usmovientv.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.MovieDetails;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;
import mobile.kamheisiu.usmovientv.databinding.ActivityMovieDetailsBinding;
import mobile.kamheisiu.usmovientv.viewmodel.MovieDetailsActivityViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private int mMovieId;
    public static final String MOVIE_ID_KEY = "MOVIE_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Intent intent = getIntent();
        if (intent != null) {
            mMovieId = intent.getIntExtra(MOVIE_ID_KEY, -1);
        }

        if (mMovieId != -1) {
            getMovieDetails();
        }
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
                        MovieDetailsActivityViewModel viewModel = new MovieDetailsActivityViewModel(movieDetails, MovieDetailsActivity.this);
                        binding.setMdavm(viewModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
