package mobile.kamheisiu.usmovientv.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.fragment.movies.NowPlayingMoviesFragment;
import mobile.kamheisiu.usmovientv.fragment.tvshows.OnTheAirTVShowsFragment;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class SearchFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SearchFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new NowPlayingMoviesFragment();
        switch (position) {
            case 0:
                fragment = new NowPlayingMoviesFragment();
                break;
            case 1:
                fragment = new OnTheAirTVShowsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = mContext.getString(R.string.menu_item_movie);
        switch (position) {
            case 0:
                title = mContext.getString(R.string.menu_item_movie);
                break;
            case 1:
                title = mContext.getString(R.string.menu_item_tv);
                break;
        }

        return title;
    }
}
