package mobile.kamheisiu.usmovientv.data.remote;

import android.util.Log;

import mobile.kamheisiu.usmovientv.data.model.GetMoviesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kamheisiu on 12/11/2017.
 */

public class CallBackWithLogging<T> implements Callback<T> {

    private String apiName;

    public CallBackWithLogging(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
    }
}
