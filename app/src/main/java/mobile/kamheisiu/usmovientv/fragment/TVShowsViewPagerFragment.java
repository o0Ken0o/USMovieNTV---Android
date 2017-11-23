package mobile.kamheisiu.usmovientv.fragment;

import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.adapter.TVShowsFragmentPagerAdapter;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TVShowsViewPagerFragment extends ViewPagerFragment {
    @Override
    protected FragmentPagerAdapter getAdapter() {
        return new TVShowsFragmentPagerAdapter(getChildFragmentManager(), this.getContext());
    }
}
