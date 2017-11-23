package mobile.kamheisiu.usmovientv.fragment;

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
import mobile.kamheisiu.usmovientv.adapter.MoviesFragmentAdapter;
import mobile.kamheisiu.usmovientv.data.model.Movie;
import mobile.kamheisiu.usmovientv.databinding.FragmentMoviesBinding;
import mobile.kamheisiu.usmovientv.viewmodel.MoviesFragmentViewModel;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public class MoviesFragment extends Fragment {

    public static final String TAG = "MoviesFragment";
    public static final String TITLE_KEY = "TITLE_KEY";

    private FragmentMoviesBinding binding;
    private String title;

    private MoviesFragmentViewModel mMoviesFragmentViewModel;

    // TODO: subclass the Observer class of RxJava2 to have global error logging and google if this is the right way to do it

    public static MoviesFragment newInstance(String title) {
        MoviesFragment fragment = new MoviesFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);

        return fragment;
    }

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);

        binding.swipeRefreshLayout.setOnRefreshListener(() -> { onRefresh(); });

        mMoviesFragmentViewModel = new MoviesFragmentViewModel();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mMoviesFragmentViewModel.getCurrentGetMoviesList() == null) {
            showSpinnerOnly();
            subscribeToGetMoviesResponse(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mMoviesFragmentViewModel.getGetMoviesResponse().unsubscribeOn(Schedulers.io());
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
        Consumer<MoviesFragmentViewModel.MoviesRequestResponse> onNext = moviesRequestResponse -> { onReceiveResponse(moviesRequestResponse); };

        if (isCreateNewVM) {
            mMoviesFragmentViewModel = new MoviesFragmentViewModel();
        }

        mMoviesFragmentViewModel.getGetMoviesResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }

    private void onReceiveResponse(MoviesFragmentViewModel.MoviesRequestResponse response) {
        binding.spinnerLoader.setVisibility(View.INVISIBLE);
        binding.swipeRefreshLayout.setEnabled(true);

        if (response.isSuccessful()) {
            showMoviesList(response.getGetMoviesList().getMovies());
        } else {

            if (response.isRefresh() && mMoviesFragmentViewModel.getCurrentGetMoviesList() != null) {
                // we are already displaying a list
                showHideComponents(false, false, true);
                Toast.makeText(this.getContext(), getString(R.string.restful_call_try_again), Toast.LENGTH_SHORT).show();
            } else {
                showHideComponents(false, true, false);
            }
        }
    }

    private void showMoviesList(List<Movie> movies) {
        MoviesFragmentAdapter adapter = new MoviesFragmentAdapter(MoviesFragment.this.getContext(), movies);
        binding.recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MoviesFragment.this.getContext(), 3);
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        showHideComponents(false, false, true);
    }

    private void onRefresh() {
        mMoviesFragmentViewModel.getGetMoviesResponse().unsubscribeOn(Schedulers.io());

        binding.swipeRefreshLayout.setRefreshing(false);
        binding.swipeRefreshLayout.setEnabled(false);
        mMoviesFragmentViewModel.onRefresh();

        showSpinnerOnly();
        subscribeToGetMoviesResponse(false);
    }
}
