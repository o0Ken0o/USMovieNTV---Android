package mobile.kamheisiu.usmovientv.fragment.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.adapter.MoviesFragmentAdapter;
import mobile.kamheisiu.usmovientv.adapter.TVShowsFragmentAdapter;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;
import mobile.kamheisiu.usmovientv.databinding.FragmentSearchBinding;
import mobile.kamheisiu.usmovientv.viewmodel.search.SearchViewPagerFragmentViewModel;

/**
 * Created by kamheisiu on 24/11/2017.
 */

public class SearchViewPagerFragment extends Fragment {

    public class SearchRequest {
        String keywords;
        int tabIndex;

        public SearchRequest(String keywords, int tabIndex) {
            this.keywords = keywords;
            this.tabIndex = tabIndex;
        }

        public String getKeywords() {
            return keywords;
        }

        public int getTabIndex() {
            return tabIndex;
        }
    }

    private FragmentSearchBinding binding;
    private BehaviorSubject<SearchRequest> searchRequest;
    private SearchViewPagerFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        setupTabLayout();

        setupSearchView();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        searchRequest = BehaviorSubject.create();
        viewModel = new SearchViewPagerFragmentViewModel(searchRequest, getContext());
        setupDataListener();
    }

    @Override
    public void onPause() {
        super.onPause();

        searchRequest.onComplete();
        viewModel.getGetMoviesList().unsubscribeOn(Schedulers.io());
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) SearchViewPagerFragment.this
                .getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRequest.onNext(new SearchRequest(query, binding.tabLayout.getSelectedTabPosition()));
                hideKeyboard(binding.searchView);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void setupTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.menu_item_movie));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.menu_item_tv));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SearchViewPagerFragment.this.onTabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void onTabSelected(TabLayout.Tab tab) {

    }

    private void setupDataListener() {
        setupMoviesDataListener();
        setupTVShowsDataListener();
    }

    private void setupMoviesDataListener() {
        viewModel.getGetMoviesList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMoviesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetMoviesList getMoviesList) {
                        MoviesFragmentAdapter adapter = new MoviesFragmentAdapter(SearchViewPagerFragment.this.getContext(), getMoviesList.getMovies());
                        binding.recyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchViewPagerFragment.this.getContext(), 3);
                        binding.recyclerView.setLayoutManager(gridLayoutManager);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setupTVShowsDataListener() {
        viewModel.getGetTVShowsList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetTVShowsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetTVShowsList getTVShowsList) {
                        TVShowsFragmentAdapter adapter = new TVShowsFragmentAdapter(getContext(), getTVShowsList.getTVShows());
                        binding.recyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                        binding.recyclerView.setLayoutManager(gridLayoutManager);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
