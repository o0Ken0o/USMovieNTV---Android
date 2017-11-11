package mobile.kamheisiu.usmovientv.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.databinding.FragmentMoviesBinding;

/**
 * Created by kamheisiu on 11/11/2017.
 */

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    public static final String TITLE_KEY = "TITLE_KEY";
    private String title;

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
        binding.title.setText(title);
        return binding.getRoot();
    }


}
