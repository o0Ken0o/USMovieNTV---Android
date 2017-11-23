package mobile.kamheisiu.usmovientv.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.fragment.MoviesFragment;
import mobile.kamheisiu.usmovientv.fragment.NowPlayingMoviesFragment;
import mobile.kamheisiu.usmovientv.fragment.PopularMoviesFragment;
import mobile.kamheisiu.usmovientv.fragment.TopRatedMoviesFragment;
import mobile.kamheisiu.usmovientv.fragment.UpComingMoviesFragment;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TVShowsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TVShowsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        MoviesFragment fragment = new NowPlayingMoviesFragment();

        switch (position) {
            case 0:
                fragment = NowPlayingMoviesFragment.newInstance(mContext.getString(R.string.movies_view_pager_now_playing));
                break;
            case 1:
                fragment = NowPlayingMoviesFragment.newInstance(mContext.getString(R.string.movies_view_pager_now_playing));
                break;
            case 2:
                fragment = NowPlayingMoviesFragment.newInstance(mContext.getString(R.string.movies_view_pager_now_playing));
                break;
            case 3:
                fragment = NowPlayingMoviesFragment.newInstance(mContext.getString(R.string.movies_view_pager_now_playing));
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position) {
            case 0:
                title =  mContext.getString(R.string.movies_view_pager_now_playing);
                break;
            case 1:
                title =  mContext.getString(R.string.movies_view_pager_now_playing);
                break;
            case 2:
                title =  mContext.getString(R.string.movies_view_pager_now_playing);
                break;
            case 3:
                title =  mContext.getString(R.string.movies_view_pager_now_playing);
                break;
        }

        return title;
    }
}
