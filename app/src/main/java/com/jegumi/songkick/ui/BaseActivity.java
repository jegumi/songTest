package com.jegumi.songkick.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.songkick.R;

public class BaseActivity extends ActionBarActivity {

    public void setActionBar(boolean isHome) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(!isHome);
    }

    // Public for testing
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }
}