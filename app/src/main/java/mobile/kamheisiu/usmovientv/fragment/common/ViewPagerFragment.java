package mobile.kamheisiu.usmovientv.fragment.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.databinding.FragmentViewpagerBinding;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public abstract class ViewPagerFragment extends Fragment {

    private FragmentViewpagerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewpager, container, false);

        FragmentPagerAdapter adapter = getAdapter();
        binding.viewPager.setAdapter(adapter);

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }

    protected abstract FragmentPagerAdapter getAdapter();
}
