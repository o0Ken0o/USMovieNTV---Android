package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.PopularMoviesFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class PopularMoviesFragment extends MoviesFragment {

    public static PopularMoviesFragment newInstance(String title) {
        PopularMoviesFragment fragment = new PopularMoviesFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mMoviesFragmentViewModel = new PopularMoviesFragmentViewModel();
    }
}
