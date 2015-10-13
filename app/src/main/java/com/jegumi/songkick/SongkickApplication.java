package com.jegumi.songkick;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SongkickApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPreferences();
    }

    private void initPreferences() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("accessToken", "");
        editor.commit();
    }
}
