package mobile.kamheisiu.usmovientv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import mobile.kamheisiu.usmovientv.data.remote.ApiUtils;
import mobile.kamheisiu.usmovientv.data.remote.MoviesServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesServices moviesServices = new ApiUtils().getMoviesServices();
        moviesServices.getNowPlaying().enqueue(new Callback<GetMoviesList>() {
            @Override
            public void onResponse(Call<GetMoviesList> call, Response<GetMoviesList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getNowPlaying size(): " + response.body().getMovies().size());
                } else {
                    Log.d(TAG, "Not successful");
                }
            }

            @Override
            public void onFailure(Call<GetMoviesList> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });

        moviesServices.getPopular().enqueue(new Callback<GetMoviesList>() {
            @Override
            public void onResponse(Call<GetMoviesList> call, Response<GetMoviesList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getPopular size(): " + response.body().getMovies().size());
                } else {
                    Log.d(TAG, "Not successful");
                }
            }

            @Override
            public void onFailure(Call<GetMoviesList> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });

        moviesServices.getTopRated().enqueue(new Callback<GetMoviesList>() {
            @Override
            public void onResponse(Call<GetMoviesList> call, Response<GetMoviesList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getTopRated size(): " + response.body().getMovies().size());
                } else {
                    Log.d(TAG, "Not successful");
                }
            }

            @Override
            public void onFailure(Call<GetMoviesList> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });

        moviesServices.getUpComing().enqueue(new Callback<GetMoviesList>() {
            @Override
            public void onResponse(Call<GetMoviesList> call, Response<GetMoviesList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "getUpComing size(): " + response.body().getMovies().size());
                } else {
                    Log.d(TAG, "Not successful");
                }
            }

            @Override
            public void onFailure(Call<GetMoviesList> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });
    }
}
