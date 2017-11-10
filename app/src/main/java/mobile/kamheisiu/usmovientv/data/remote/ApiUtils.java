package mobile.kamheisiu.usmovientv.data.remote;

/**
 * Created by kamheisiu on 9/11/2017.
 */

public class ApiUtils {
//    public static String BASE_URL = "https://api.themoviedb.org/3/";
    public static String BASE_URL = "https://google.com.hk:81/";
    public static final String API_KEY_KEY = "api_key";
    public static final String API_KEY_VALUE = "ba0a292f6231bfc33b918d9c7cb31095";

    public MoviesServices getMoviesServices() {
        return ApiClient.getClient(BASE_URL).create(MoviesServices.class);
    }
}
