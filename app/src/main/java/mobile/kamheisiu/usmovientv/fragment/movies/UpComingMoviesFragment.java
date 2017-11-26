package mobile.kamheisiu.usmovientv.fragment.movies;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.movies.UpComingMoviesFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class UpComingMoviesFragment extends MoviesFragment {

    public static UpComingMoviesFragment newInstance(String title) {
        UpComingMoviesFragment fragment = new UpComingMoviesFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mMoviesFragmentViewModel = new UpComingMoviesFragmentViewModel();
    }
}