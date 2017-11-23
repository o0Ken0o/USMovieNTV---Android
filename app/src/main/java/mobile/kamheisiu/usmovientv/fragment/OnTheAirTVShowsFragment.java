package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.AiringTodayTVShowsFragmentViewModel;
import mobile.kamheisiu.usmovientv.viewmodel.OnTheAirTVShowsFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class OnTheAirTVShowsFragment extends TVShowsFragment {

    public static OnTheAirTVShowsFragment newInstance(String title) {
        OnTheAirTVShowsFragment fragment = new OnTheAirTVShowsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mTVShowsFragmentViewModel = new OnTheAirTVShowsFragmentViewModel();
    }
}
