package mobile.kamheisiu.usmovientv.fragment.movies;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.movies.TopRatedMoviesFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TopRatedMoviesFragment extends MoviesFragment {

    public static TopRatedMoviesFragment newInstance(String title) {
        TopRatedMoviesFragment fragment = new TopRatedMoviesFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mMoviesFragmentViewModel = new TopRatedMoviesFragmentViewModel();
    }
}
