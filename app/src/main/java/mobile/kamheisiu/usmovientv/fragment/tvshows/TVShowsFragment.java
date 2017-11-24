package mobile.kamheisiu.usmovientv.fragment.tvshows;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.adapter.TVShowsFragmentAdapter;
import mobile.kamheisiu.usmovientv.data.model.TVShow;
import mobile.kamheisiu.usmovientv.data.remote.TVShowsRequestResponse;
import mobile.kamheisiu.usmovientv.databinding.FragmentTvShowsBinding;
import mobile.kamheisiu.usmovientv.viewmodel.tvshows.TVShowsFragmentViewModel;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public abstract class TVShowsFragment extends Fragment {

    public static final String TAG = "TVShowsFragment";
    public static final String TITLE_KEY = "TITLE_KEY";

    private FragmentTvShowsBinding binding;
    private String title;

    protected TVShowsFragmentViewModel mTVShowsFragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(TITLE_KEY, "Title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_shows, container, false);

        binding.swipeRefreshLayout.setOnRefreshListener(() -> { onRefresh(); });

        initViewModel();

        return binding.getRoot();
    }

    protected abstract void initViewModel();

    @Override
    public void onResume() {
        super.onResume();

        if (mTVShowsFragmentViewModel.getCurrentTVShowsList() == null) {
            showSpinnerOnly();
            subscribeToGetMoviesResponse(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mTVShowsFragmentViewModel.getTVShowsRequestResponse().unsubscribeOn(Schedulers.io());
    }

    private void showHideComponents(boolean ifSpinnerVisible, boolean ifErrorMsgVisible, boolean ifRecyclerViewVisible) {
        binding.spinnerLoader.setVisibility(ifSpinnerVisible ? View.VISIBLE : View.INVISIBLE);
        binding.errorMsg.setVisibility(ifErrorMsgVisible ? View.VISIBLE : View.INVISIBLE);
        binding.recyclerView.setVisibility(ifRecyclerViewVisible ? View.VISIBLE : View.INVISIBLE);
    }

    private void showSpinnerOnly() {
        showHideComponents(true, false, false);
    }

    private void subscribeToGetMoviesResponse(boolean isCreateNewVM) {
        Consumer<TVShowsRequestResponse> onNext = tvShowsRequestResponse -> { onReceiveResponse(tvShowsRequestResponse); };

        if (isCreateNewVM) {
            initViewModel();
        }

        mTVShowsFragmentViewModel.getTVShowsRequestResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }

    private void onReceiveResponse(TVShowsRequestResponse response) {
        binding.spinnerLoader.setVisibility(View.INVISIBLE);
        binding.swipeRefreshLayout.setEnabled(true);

        if (response.isSuccessful()) {
            showTVShowsList(response.getGetTVShowsList().getTVShows());
        } else {

            if (response.isRefresh() && mTVShowsFragmentViewModel.getCurrentTVShowsList() != null) {
                // we are already displaying a list
                showHideComponents(false, false, true);
                Toast.makeText(this.getContext(), getString(R.string.restful_call_try_again), Toast.LENGTH_SHORT).show();
            } else {
                showHideComponents(false, true, false);
            }
        }
    }

    private void showTVShowsList(List<TVShow> tvShows) {
        TVShowsFragmentAdapter adapter = new TVShowsFragmentAdapter(getContext(), tvShows);
        binding.recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        showHideComponents(false, false, true);
    }

    private void onRefresh() {
        mTVShowsFragmentViewModel.getTVShowsRequestResponse().unsubscribeOn(Schedulers.io());

        binding.swipeRefreshLayout.setRefreshing(false);
        binding.swipeRefreshLayout.setEnabled(false);
        mTVShowsFragmentViewModel.onRefresh();

        showSpinnerOnly();
        subscribeToGetMoviesResponse(false);
    }
}
