package mobile.kamheisiu.usmovientv.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.activity.MovieDetailsActivity;
import mobile.kamheisiu.usmovientv.activity.TVShowDetailsActivity;
import mobile.kamheisiu.usmovientv.data.model.Movie;
import mobile.kamheisiu.usmovientv.data.model.TVShow;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public class ItemTVShowViewModel extends BaseObservable {

    private TVShow mTVShow;
    private Context mContext;

    public ItemTVShowViewModel(TVShow tvShow, Context context) {
        mTVShow = tvShow;
        mContext = context;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String relativeUrl) {
        Log.d("debug3", "loadImage: " + relativeUrl);

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(ApiUtils.IMAGE_BASE_URL)
                .append(ApiUtils.IMAGE_WIDTH_PATH)
                .append(relativeUrl);

        Glide.with(view.getContext())
                .load(urlBuilder.toString())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.movietv_clear_bg)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(view);
    }

    public View.OnClickListener onItemClick() {
        return v -> {
            Intent intent = new Intent(mContext, TVShowDetailsActivity.class);
            intent.putExtra(TVShowDetailsActivity.TV_SHOW_ID_KEY, mTVShow.getId());
            mContext.startActivity(intent);
        };
    }

    public String getReleaseDate() {
        return mTVShow.getFirstAirDate();
    }

    public String getPosterUrl() {
        return mTVShow.getPosterPath();
    }

    public String getPopularity() {
        return String.format("%.2f", mTVShow.getPopularity());
    }
}
