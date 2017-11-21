package mobile.kamheisiu.usmovientv.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.Movie;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;

import static mobile.kamheisiu.usmovientv.data.remote.ApiUtils.IMAGE_BASE_URL;

/**
 * Created by kamheisiu on 12/11/2017.
 */

public class ItemMovieViewModel extends BaseObservable {

    private Movie mMovie;
    private Context mContext;

    public ItemMovieViewModel(Movie movie, Context context) {
        mMovie = movie;
        mContext = context;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String relativeUrl) {
        // TODO: add a placeholder for the image view later on
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
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug3", "onItemClick: " + mMovie.getId());
            }
        };
    }

    public String getReleaseDate() {
        return mMovie.getReleaseDate();
    }

    public String getPosterUrl() {
        return mMovie.getPosterPath();
    }

    public String getPopularity() {
        return String.format("%.2f", mMovie.getPopularity());
    }
}
