package mobile.kamheisiu.usmovientv.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.adapter.MoviesFragmentPagerAdapter;
import mobile.kamheisiu.usmovientv.databinding.FragmentMoviesViewpagerBinding;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public class MoviesViewPagerFragment extends Fragment {

    private FragmentMoviesViewpagerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_viewpager, container, false);

        MoviesFragmentPagerAdapter adapter = new MoviesFragmentPagerAdapter(getChildFragmentManager(), this.getContext());
        binding.viewPager.setAdapter(adapter);

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }
}
