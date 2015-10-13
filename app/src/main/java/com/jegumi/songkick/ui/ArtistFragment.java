package com.jegumi.songkick.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jegumi.songkick.R;
import com.jegumi.songkick.model.Artist;

public class ArtistFragment extends Fragment {

    private TextView displayNameTextView;
    private TextView onTourTextView;
    private TextView uriTextView;

    public static ArtistFragment newInstance(Artist artist) {
        ArtistFragment artistFragment = new ArtistFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.EXTRA_ARTIST, artist);
        artistFragment.setArguments(bundle);
        return artistFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.artist, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).setActionBar(false);

        displayNameTextView = (TextView) view.findViewById(com.jegumi.songkick.R.id.displayname_text_view);
        onTourTextView = (TextView) view.findViewById(com.jegumi.songkick.R.id.ontour_text_view);
        uriTextView = (TextView) view.findViewById(com.jegumi.songkick.R.id.uri_text_view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Artist artist = (Artist) arguments.getSerializable(MainActivity.EXTRA_ARTIST);
            initFields(artist);
        }
    }

    private void initFields(Artist artist) {
        displayNameTextView.setText(artist.displayName);
        onTourTextView.setText(getString(com.jegumi.songkick.R.string.on_tour, artist.onTourUntil));
        uriTextView.setText(getString(com.jegumi.songkick.R.string.uri, artist.uri));
    }
}
