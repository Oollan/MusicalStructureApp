package com.example.oollan.musicalstructureapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SongViewHolder> {

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, int position) {
        holder.songTV.setText(Song.callList()[position].getSongName());
        holder.artistTV.setText(Song.callList()[position].getArtistName());
        holder.albumTV.setText(Song.callList()[position].getAlbumName());
    }

    @Override
    public int getItemCount() {
        return Song.callList().length;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {

       private TextView songTV;
       private TextView artistTV;
       private TextView albumTV;

        SongViewHolder(View recyclerItem) {
            super(recyclerItem);
            songTV = recyclerItem.findViewById(R.id.ITEM_songTV);
            artistTV = recyclerItem.findViewById(R.id.ITEM_artistTV);
            albumTV = recyclerItem.findViewById(R.id.ITEM_albumTV);
        }
    }
}