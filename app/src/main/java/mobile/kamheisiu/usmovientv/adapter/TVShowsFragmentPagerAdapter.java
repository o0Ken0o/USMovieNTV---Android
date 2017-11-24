package mobile.kamheisiu.usmovientv.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.fragment.AiringTodayTVShowsFragment;
import mobile.kamheisiu.usmovientv.fragment.OnTheAirTVShowsFragment;
import mobile.kamheisiu.usmovientv.fragment.PopularTVShowsFragment;
import mobile.kamheisiu.usmovientv.fragment.TVShowsFragment;
import mobile.kamheisiu.usmovientv.fragment.TopRatedTVShowsFragment;

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
        TVShowsFragment fragment = new AiringTodayTVShowsFragment();

        switch (position) {
            case 0:
                fragment = AiringTodayTVShowsFragment.newInstance(mContext.getString(R.string.tv_shows_view_pager_airing_today));
                break;
            case 1:
                fragment = OnTheAirTVShowsFragment.newInstance(mContext.getString(R.string.tv_shows_view_pager_on_the_air));
                break;
            case 2:
                fragment = PopularTVShowsFragment.newInstance(mContext.getString(R.string.tv_shows_view_pager_popular));
                break;
            case 3:
                fragment = TopRatedTVShowsFragment.newInstance(mContext.getString(R.string.tv_shows_view_pager_top_rated));
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
                title =  mContext.getString(R.string.tv_shows_view_pager_airing_today);
                break;
            case 1:
                title =  mContext.getString(R.string.tv_shows_view_pager_on_the_air);
                break;
            case 2:
                title =  mContext.getString(R.string.tv_shows_view_pager_popular);
                break;
            case 3:
                title =  mContext.getString(R.string.tv_shows_view_pager_top_rated);
                break;
        }

        return title;
    }
}
