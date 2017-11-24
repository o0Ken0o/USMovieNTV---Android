package mobile.kamheisiu.usmovientv.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.databinding.ActivityMovieDetailsBinding;
import mobile.kamheisiu.usmovientv.viewmodel.movies.MovieDetailsActivityViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieDetailsActivityViewModel viewModel;
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

        binding.progressSpinner.setVisibility(View.VISIBLE);
        binding.contentScrollView.setVisibility(View.INVISIBLE);

        viewModel = new MovieDetailsActivityViewModel(MovieDetailsActivity.this, mMovieId);
        viewModel.getIsDataReady().subscribe(this::displayData);
    }

    public void displayData(Boolean isDataReady) {
        if (isDataReady) {
            binding.setMdavm(viewModel);
            binding.contentScrollView.setVisibility(View.VISIBLE);
        }

        binding.progressSpinner.setVisibility(View.GONE);
    }
}
