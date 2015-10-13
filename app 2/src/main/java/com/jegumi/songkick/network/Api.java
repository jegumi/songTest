package com.jegumi.songkick.network;

import java.util.HashMap;

public class Api {

    private static final String TAG = Api.class.getSimpleName();
    private static final String BASE_URL = "http://api.songkick.com/api/3.0";
    private static final String SEARCH_ENDPOINT = "/search/artists.json";
    private static final String QUERY_KEY = "query";
    private static final String API_KEY = "apikey";
    private static final String API_VALUE = "jhevSy2yQF6HFzmb";

    public static String getSearchEndPoint() {
        return BASE_URL + SEARCH_ENDPOINT;
    }

    public static String getSearchUrl(String searchText) {
        return getSearchEndPoint() + "?" + QUERY_KEY + "=" + searchText + "&" + API_KEY + "=" + API_VALUE;
    }

    public static HashMap<String, String> getHeaders() {
        HashMap headers = new HashMap<>();
        headers.put(API_KEY, API_VALUE);

        return headers;
    }
}
