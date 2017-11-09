package mobile.kamheisiu.usmovientv.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kamheisiu on 9/11/2017.
 */

public class ApiClient {
    private static Retrofit mRetrofit = null;

    private static OkHttpClient client = null;

    public static Retrofit getClient(String baseUrl) {
        if (client == null) {
            client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .addQueryParameter(ApiUtils.API_KEY_KEY,ApiUtils.API_KEY_VALUE)
                            .build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }).build();

        }

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return mRetrofit;
    }
}
