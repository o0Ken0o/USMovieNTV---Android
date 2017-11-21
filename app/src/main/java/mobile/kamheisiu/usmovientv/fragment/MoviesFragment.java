package mobile.kamheisiu.usmovientv.fragment;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.adapter.MoviesFragmentAdapter;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.CallBackWithLogging;
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;
import mobile.kamheisiu.usmovientv.databinding.FragmentMoviesBinding;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public class MoviesFragment extends Fragment {

    public static final String TAG = "MoviesFragment";
    private FragmentMoviesBinding binding;
    public static final String TITLE_KEY = "TITLE_KEY";
    private String title;

    // TODO: subclass the Observer class of RxJava2 to have global error logging and google if this is the right way to do it
    // TODO: add a loader while fetching data from the server. (use use RxJava to show or hide it)
    // TODO: add a placeholder for loading image

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
        getNowPlayingMovies();
        return binding.getRoot();
    }

    private void getNowPlayingMovies() {
        MoviesServices moviesServices = new ApiUtils().getMoviesServices();

        moviesServices.getNowPlaying().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMoviesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        binding.spinnerLoader.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(GetMoviesList getMoviesList) {
                        MoviesFragmentAdapter adapter = new MoviesFragmentAdapter(MoviesFragment.this.getContext(), getMoviesList.getMovies());
                        binding.recyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(MoviesFragment.this.getContext(), 3);
                        binding.recyclerView.setLayoutManager(gridLayoutManager);
                        binding.spinnerLoader.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleRequestFailure(e);
                        binding.spinnerLoader.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        binding.spinnerLoader.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void handleRequestFailure(Throwable t) {
        // When the Throwable passed to the failure callback is an IOException,
        // this means that it was a network problem (socket timeout, unknown host, etc.).
        // Any other exception means something broke either in serializing/deserializing the data
        // or it's a configuration problem
        if (t instanceof IOException) {
            handleRequestNetworkError(t);
        } else {
            handleRequestNonNetworkError(t);
        }
    }

    private void handleRequestNetworkError(Throwable t) {
        Log.d(TAG, "handleRequestNetworkError: " + t.getMessage());
    }

    private void handleRequestNonNetworkError(Throwable t) {
        Log.d(TAG, "handleRequestNonNetworkError: " + t.getMessage());
    }
}
