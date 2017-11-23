package mobile.kamheisiu.usmovientv.data.model;

/**
 * Created by kamheisiu on 9/11/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMoviesList {

    @SerializedName("results")
    @Expose
    private List<Movie> mMovies = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("dates")
    @Expose
    private NowPlayingDateRange mNowPlayingDateRange;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        this.mMovies = movies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public NowPlayingDateRange getNowPlayingDateRange() {
        return mNowPlayingDateRange;
    }

    public void setNowPlayingDateRange(NowPlayingDateRange nowPlayingDateRange) {
        this.mNowPlayingDateRange = nowPlayingDateRange;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
