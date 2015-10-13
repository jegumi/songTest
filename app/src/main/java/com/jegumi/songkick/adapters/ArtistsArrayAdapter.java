package com.jegumi.songkick.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jegumi.songkick.model.Artist;

import java.util.List;

public class ArtistsArrayAdapter extends ArrayAdapter<Artist> {

    public static class ViewHolder {
        private TextView captionTextView;
    }

    private Context context;

    public ArtistsArrayAdapter(Context context, List<Artist> objects) {
        super(context, com.jegumi.songkick.R.layout.search_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Artist artist = getItem(position);
        if (convertView == null) {
            convertView = inflateView();
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.captionTextView.setText(artist.displayName);

        return convertView;
    }

    private View inflateView() {
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(com.jegumi.songkick.R.layout.search_item, null);

        ViewHolder holder = new ViewHolder();
        holder.captionTextView = (TextView) view.findViewById(com.jegumi.songkick.R.id.artist_name_text_view);
        view.setTag(holder);

        return view;
    }
}

