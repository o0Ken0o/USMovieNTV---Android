package mobile.kamheisiu.usmovientv.data.remote;

/**
 * Created by kamheisiu on 26/11/2017.
 */

public class SearchRequest {
    String keywords;
    int tabIndex;

    public SearchRequest(String keywords, int tabIndex) {
        this.keywords = keywords;
        this.tabIndex = tabIndex;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getTabIndex() {
        return tabIndex;
    }
}
