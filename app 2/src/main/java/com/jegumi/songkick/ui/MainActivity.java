package com.jegumi.songkick.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;

import com.jegumi.songkick.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    public static final String EXTRA_ARTIST = "com.jegumi.songkick.artist";
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTablet()) {
            setContentView(R.layout.main_layout);
        }
        loadFragment();
    }

    private void loadFragment() {
        int layoutDestination = isTablet() ? R.id.left_layout : android.R.id.content;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MainSearchFragment startFragment = MainSearchFragment.newInstance();
        ft.replace(layoutDestination, startFragment, TAG);
        ft.commit();
    }

    // Public for testing
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }
}