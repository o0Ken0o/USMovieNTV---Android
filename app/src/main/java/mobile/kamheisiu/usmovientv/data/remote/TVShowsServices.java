package mobile.kamheisiu.usmovientv.data.remote;

import io.reactivex.Observable;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.model.GetTVShowsList;
import mobile.kamheisiu.usmovientv.data.model.MovieDetails;
import mobile.kamheisiu.usmovientv.data.model.TVShowDetails;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kamheisiu on 23/11/2017.
 */

public interface TVShowsServices {
    @GET("tv/airing_today")
    Observable<GetTVShowsList> getAiringToday();

    @GET("tv/on_the_air")
    Observable<GetTVShowsList> getOnTheAir();

    @GET("tv/popular")
    Observable<GetTVShowsList> getPopular();

    @GET("tv/top_rated")
    Observable<GetTVShowsList> getTopRated();

    @GET("tv/{id}")
    Observable<TVShowDetails> getTVShowDetails(@Path("id") int tvShowId);
}
