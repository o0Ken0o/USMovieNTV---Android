package mobile.kamheisiu.usmovientv.fragment;

import android.os.Bundle;

import mobile.kamheisiu.usmovientv.viewmodel.AiringTodayTVShowsFragmentViewModel;
import mobile.kamheisiu.usmovientv.viewmodel.PopularTVShowsFragmentViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class PopularTVShowsFragment extends TVShowsFragment {

    public static PopularTVShowsFragment newInstance(String title) {
        PopularTVShowsFragment fragment = new PopularTVShowsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void initViewModel() {
        mTVShowsFragmentViewModel = new PopularTVShowsFragmentViewModel();
    }
}
