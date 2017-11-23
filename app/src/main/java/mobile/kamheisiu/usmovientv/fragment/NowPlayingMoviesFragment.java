package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.NowPlayingMoviesFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class NowPlayingMoviesFragment extends MoviesFragment {

    public static NowPlayingMoviesFragment newInstance(String title) {
        NowPlayingMoviesFragment fragment = new NowPlayingMoviesFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mMoviesFragmentViewModel = new NowPlayingMoviesFragmentViewModel();
    }
}
