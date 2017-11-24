package mobile.kamheisiu.usmovientv.fragment.search;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.reactivex.subjects.BehaviorSubject;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.adapter.SearchFragmentPagerAdapter;
import mobile.kamheisiu.usmovientv.databinding.FragmentSearchBinding;
import mobile.kamheisiu.usmovientv.viewmodel.search.SearchViewPagerFragmentViewModel;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class SearchViewPagerFragment extends Fragment {

    private FragmentSearchBinding binding;
    private BehaviorSubject<String> searchTxt;
    private SearchViewPagerFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        SearchFragmentPagerAdapter adapter = new SearchFragmentPagerAdapter(getChildFragmentManager(), getContext());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        setupSearchViewListener();

        return binding.getRoot();
    }

    private void setupSearchViewListener() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("debug3", "onQueryTextSubmit: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTxt.onNext(newText);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        searchTxt = BehaviorSubject.create();
        viewModel = new SearchViewPagerFragmentViewModel(searchTxt, getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        
        searchTxt.onComplete();
    }
}
