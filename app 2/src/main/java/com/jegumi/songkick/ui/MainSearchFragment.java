package com.jegumi.songkick.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jegumi.songkick.R;
import com.jegumi.songkick.adapters.ArtistsArrayAdapter;
import com.jegumi.songkick.model.Artist;
import com.jegumi.songkick.model.ResultsPage;
import com.jegumi.songkick.model.Root;
import com.jegumi.songkick.network.Api;
import com.jegumi.songkick.network.GsonRequest;
import com.jegumi.songkick.network.VolleySingleton;

import java.util.Arrays;

public class MainSearchFragment extends ListFragment {

    private static final String TAG = MainSearchFragment.class.getName();

    public static MainSearchFragment newInstance() {
        return new MainSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.jegumi.songkick.R.layout.search_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).setActionBar(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        initSearch(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getActivity(), PreferenceWithHeaders.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initSearch(Menu menu) {
        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                loadAdapter(query);
                return true;
            }

        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Artist artist = (Artist) l.getItemAtPosition(position);
        openArtist(artist);
    }

    private void loadAdapter(String artist) {
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        String uri = Api.getSearchUrl(artist);
        GsonRequest<Root> jsObjRequest = new GsonRequest<>(
                Request.Method.GET,
                uri,
                Root.class,
                new Response.Listener<Root>() {
                    @Override
                    public void onResponse(Root artists) {
                        setAdapter(artists.resultsPage);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "Error loading artists", volleyError);
                    }
                }
        );

        jsObjRequest.setTag(TAG);
        queue.add(jsObjRequest);
    }

    private void setAdapter(ResultsPage resultPage) {
        Activity parentActivity = getActivity();
        if (parentActivity != null) {
            getListView().setAdapter(new ArtistsArrayAdapter(parentActivity, Arrays.asList(resultPage.results.artist)));
        }
    }

    private void openArtist(Artist artist) {
        Intent intent = new Intent(getActivity(), ArtistActivity.class);
        intent.putExtra(MainActivity.EXTRA_ARTIST, artist);
        startActivity(intent);
    }
}
