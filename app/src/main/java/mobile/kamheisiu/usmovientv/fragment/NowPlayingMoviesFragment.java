package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.NowPlayingMoviesFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class NowPlayingMoviesFragment extends MoviesFragment {
    // TODO: subclass the Observer class of RxJava2 to have global error logging and google if this is the right way to do it

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
