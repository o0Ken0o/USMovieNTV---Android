package mobile.kamheisiu.usmovientv.fragment.tvshows;

import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.adapter.TVShowsFragmentPagerAdapter;
import mobile.kamheisiu.usmovientv.fragment.common.ViewPagerFragment;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TVShowsViewPagerFragment extends ViewPagerFragment {
    @Override
    protected FragmentPagerAdapter getAdapter() {
        return new TVShowsFragmentPagerAdapter(getChildFragmentManager(), this.getContext());
    }
}
