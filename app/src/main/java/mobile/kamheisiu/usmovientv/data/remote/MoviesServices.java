package mobile.kamheisiu.usmovientv.data.remote;

import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kamheisiu on 9/11/2017.
 */

public interface MoviesServices {
    @GET("movie/now_playing")
    Call<GetMoviesList> getNowPlaying();

    @GET("movie/popular")
    Call<GetMoviesList> getPopular();

    @GET("movie/top_rated")
    Call<GetMoviesList> getTopRated();

    @GET("movie/upcoming")
    Call<GetMoviesList> getUpComing();
}
