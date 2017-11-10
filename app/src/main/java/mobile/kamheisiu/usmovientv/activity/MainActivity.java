package mobile.kamheisiu.usmovientv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

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
                handleRequestFailure(t);
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
