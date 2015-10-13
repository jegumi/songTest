package com.jegumi.songkick.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jegumi.songkick.model.Artist;

public class ArtistActivity extends BaseActivity {
    private static final String TAG = ArtistActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment((Artist) getIntent().getSerializableExtra(MainActivity.EXTRA_ARTIST));
    }

    private void loadFragment(Artist artist) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ArtistFragment startFragment = ArtistFragment.newInstance(artist);
        ft.replace(android.R.id.content, startFragment, TAG);
        ft.commit();
    }
}