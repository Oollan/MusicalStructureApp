package com.example.oollan.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class RecyclerActivity extends MainActivity {

    private TextView songTV;
    private TextView artistTV;
    private TextView albumTV;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (RecyclerActivity.this));
        recyclerView.setItemViewCacheSize(100);
        recyclerView.scrollToPosition(POSITION);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener
                (new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (hasWindowFocus()) {
                            songTV = recyclerView.getLayoutManager()
                                    .findViewByPosition(POSITION).findViewById(R.id.ITEM_songTV);
                            artistTV = recyclerView.getLayoutManager()
                                    .findViewByPosition(POSITION).findViewById(R.id.ITEM_artistTV);
                            albumTV = recyclerView.getLayoutManager()
                                    .findViewByPosition(POSITION).findViewById(R.id.ITEM_albumTV);
                            songTV.setTextColor(getColor(R.color.colorPrimary));
                            artistTV.setTextColor(getColor(R.color.colorPrimary));
                            albumTV.setTextColor(getColor(R.color.colorPrimary));
                        }
                    }
                });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (this, recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                if (songTV != null) {
                                    songTV.setTextColor(getColor(R.color.gray));
                                    artistTV.setTextColor(getColor(R.color.gray));
                                    albumTV.setTextColor(getColor(R.color.gray));
                                }
                                songTV = view.findViewById(R.id.ITEM_songTV);
                                artistTV = view.findViewById(R.id.ITEM_artistTV);
                                albumTV = view.findViewById(R.id.ITEM_albumTV);
                                songTV.setTextColor(getColor(R.color.colorPrimary));
                                artistTV.setTextColor(getColor(R.color.colorPrimary));
                                albumTV.setTextColor(getColor(R.color.colorPrimary));
                                POSITION = position;
                                PROGRESS = 0;
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                if (songTV != null) {
                                    songTV.setTextColor(getColor(R.color.gray));
                                    artistTV.setTextColor(getColor(R.color.gray));
                                    albumTV.setTextColor(getColor(R.color.gray));
                                }
                                songTV = view.findViewById(R.id.ITEM_songTV);
                                artistTV = view.findViewById(R.id.ITEM_artistTV);
                                albumTV = view.findViewById(R.id.ITEM_albumTV);
                                songTV.setTextColor(getColor(R.color.colorPrimary));
                                artistTV.setTextColor(getColor(R.color.colorPrimary));
                                albumTV.setTextColor(getColor(R.color.colorPrimary));
                                POSITION = position;
                                PROGRESS = 0;
                                onBackPressed();
                            }
                        })
        );
    }
}