package mobile.kamheisiu.usmovientv.fragment.tvshows;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.tvshows.AiringTodayTVShowsFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class AiringTodayTVShowsFragment extends TVShowsFragment {

    public static AiringTodayTVShowsFragment newInstance(String title) {
        AiringTodayTVShowsFragment fragment = new AiringTodayTVShowsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mTVShowsFragmentViewModel = new AiringTodayTVShowsFragmentViewModel();
    }
}
