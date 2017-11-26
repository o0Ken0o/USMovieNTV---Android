package mobile.kamheisiu.usmovientv.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.Movie;
import mobile.kamheisiu.usmovientv.databinding.MovieItemBinding;
import mobile.kamheisiu.usmovientv.viewmodel.movies.ItemMovieViewModel;

/**
 * Created by kamheisiu on 12/11/2017.
 */

public class MoviesFragmentAdapter extends RecyclerView.Adapter<MoviesFragmentAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> movies;

    public MoviesFragmentAdapter(Context context, List<Movie> movies) {
        mContext = context;
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.binding.setMovievm(new ItemMovieViewModel(movies.get(position), mContext));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieItemBinding binding;

        public MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
