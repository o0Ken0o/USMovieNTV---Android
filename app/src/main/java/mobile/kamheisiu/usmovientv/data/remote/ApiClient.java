package mobile.kamheisiu.usmovientv.data.remote;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kamheisiu on 9/11/2017.
 */

public class ApiClient {
    private static Retrofit mRetrofit = null;

    private static OkHttpClient client = null;

    private static void postResponseHandling(Response response) {
        if (!response.isSuccessful()) {
            Log.d("debug3", "postResponseHandling: ");
        }
    }

    public static Retrofit getClient(String baseUrl) {
        if (client == null) {
            client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .addQueryParameter(ApiUtils.API_KEY_KEY,ApiUtils.API_KEY_VALUE)
                            .build();
                    request = request.newBuilder().url(url).build();

                    Response response = chain.proceed(request);
                    postResponseHandling(response);

                    return response;
                }).build();

        }

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return mRetrofit;
    }
}
