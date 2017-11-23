package mobile.kamheisiu.usmovientv.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.databinding.ActivityMainBinding;
import mobile.kamheisiu.usmovientv.fragment.MoviesViewPagerFragment;
import mobile.kamheisiu.usmovientv.fragment.TVShowsViewPagerFragment;
import mobile.kamheisiu.usmovientv.fragment.ViewPagerFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String MOVIES_VIEW_PAGER_FRAGMENT = "MOVIES_VIEW_PAGER_FRAGMENT";
    public static final String TV_SHOWS_VIEW_PAGER_FRAGMENT = "TV_SHOWS_VIEW_PAGER_FRAGMENT";
    private FragmentManager mFragmentManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        binding.bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    @Override
    protected void onResume() {
        super.onResume();

        addMoviesViewPager();
    }

    private boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_movies:
                addMoviesViewPager();
                break;
            case R.id.menu_item_tv_shows:
                addTVShowsViewPager();
                break;
            case R.id.menu_item_search:
                Log.d(TAG, "onNavigationItemSelected menu_item_search");
                break;
        }

        return true;
    }

    private void addMoviesViewPager() {
        Log.d(TAG, "onNavigationItemSelected menu_item_movies");

        MoviesViewPagerFragment fragment = (MoviesViewPagerFragment) mFragmentManager.findFragmentByTag(MOVIES_VIEW_PAGER_FRAGMENT);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new MoviesViewPagerFragment();
            fragmentTransaction.add(R.id.container_view, fragment, MOVIES_VIEW_PAGER_FRAGMENT)
                    .addToBackStack(MOVIES_VIEW_PAGER_FRAGMENT)
                    .commit();
        } else {
            hideAllFragment(fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }

    }

    private void addTVShowsViewPager() {
        Log.d(TAG, "onNavigationItemSelected menu_item_tv_shows");

        TVShowsViewPagerFragment fragment = (TVShowsViewPagerFragment) mFragmentManager.findFragmentByTag(TV_SHOWS_VIEW_PAGER_FRAGMENT);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new TVShowsViewPagerFragment();
            fragmentTransaction.add(R.id.container_view, fragment, TV_SHOWS_VIEW_PAGER_FRAGMENT)
                    .addToBackStack(TV_SHOWS_VIEW_PAGER_FRAGMENT)
                    .commit();
        } else {
            hideAllFragment(fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }
    }

    private void hideAllFragment(FragmentTransaction ft) {
        for (Fragment fragment: mFragmentManager.getFragments()) {
            ft.hide(fragment);
        }
    }

}
