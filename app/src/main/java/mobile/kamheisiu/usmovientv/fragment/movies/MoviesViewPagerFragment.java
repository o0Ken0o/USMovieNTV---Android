package mobile.kamheisiu.usmovientv.fragment.movies;

import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.adapter.MoviesFragmentPagerAdapter;
import mobile.kamheisiu.usmovientv.fragment.common.ViewPagerFragment;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class MoviesViewPagerFragment extends ViewPagerFragment {
    @Override
    protected FragmentPagerAdapter getAdapter() {
        return new MoviesFragmentPagerAdapter(getChildFragmentManager(), this.getContext());
    }
}
