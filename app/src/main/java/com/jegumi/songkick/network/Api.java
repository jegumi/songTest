package com.jegumi.songkick.network;

public class Api {

    private static final String BASE_URL = "http://api.songkick.com/api/3.0";
    private static final String SEARCH_ENDPOINT = "/search/artists.json";
    private static final String QUERY_KEY = "query";
    private static final String API_KEY = "apikey";
    private static final String API_VALUE = "YOU_API_CODE";

    public static String getSearchEndPoint() {
        return BASE_URL + SEARCH_ENDPOINT;
    }

    public static String getSearchUrl(String searchText) {
        return getSearchEndPoint() + "?" + QUERY_KEY + "=" + searchText + "&" + API_KEY + "=" + API_VALUE;
    }
}
