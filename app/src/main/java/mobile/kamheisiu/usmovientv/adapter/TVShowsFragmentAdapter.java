package mobile.kamheisiu.usmovientv.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.TVShow;
import mobile.kamheisiu.usmovientv.databinding.TvShowItemBinding;
import mobile.kamheisiu.usmovientv.viewmodel.tvshows.ItemTVShowViewModel;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class TVShowsFragmentAdapter extends RecyclerView.Adapter<TVShowsFragmentAdapter.TVShowViewHolder> {

    private Context mContext;
    private List<TVShow> tvShows;

    public TVShowsFragmentAdapter(Context context, List<TVShow> tvShows) {
        mContext = context;
        this.tvShows = tvShows;
    }

    @Override
    public TVShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TvShowItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.tv_show_item, parent, false);
        return new TVShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TVShowViewHolder holder, int position) {
        holder.binding.setTvshowvm(new ItemTVShowViewModel(tvShows.get(position), mContext));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder {

        private TvShowItemBinding binding;

        public TVShowViewHolder(TvShowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
