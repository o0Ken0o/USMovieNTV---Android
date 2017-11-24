package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.AiringTodayTVShowsFragmentViewModel;
import mobile.kamheisiu.usmovientv.viewmodel.TopRatedTVShowsFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TopRatedTVShowsFragment extends TVShowsFragment {

    public static TopRatedTVShowsFragment newInstance(String title) {
        TopRatedTVShowsFragment fragment = new TopRatedTVShowsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mTVShowsFragmentViewModel = new TopRatedTVShowsFragmentViewModel();
    }
}
