package com.example.oollan.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int POSITION;
    public static int PROGRESS;
    private static int GET_PROGRESS;
    private static long TIMER_MILLIS;
    private static boolean CLICKED;
    private TextView songTV;
    private TextView artistTV;
    private TextView albumTV;
    private FloatingActionButton fabPlay;
    private ProgressBar progressBar;
    private CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button).setBackgroundResource(android.R.drawable.ic_dialog_dialer);
        songTV = findViewById(R.id.MAIN_songTV);
        artistTV = findViewById(R.id.MAIN_artistTV);
        albumTV = findViewById(R.id.MAIN_albumTV);
        progressBar = findViewById(R.id.progressBar);
        fabPlay = findViewById(R.id.fab_play);
        fabPlay.setOnClickListener(this);
        findViewById(R.id.fab_back).setOnClickListener(this);
        findViewById(R.id.fab_next).setOnClickListener(this);
        cdt = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                if (PROGRESS <= 100) {
                    TIMER_MILLIS = l - GET_PROGRESS * 290;
                    PROGRESS = ((30000 - (int) TIMER_MILLIS) / 290);
                    progressBar.setProgress(PROGRESS);
                    if (PROGRESS > 100 && POSITION < 99) {
                        POSITION++;
                        onFinish();
                    } else if (PROGRESS == 100 && POSITION == 99) {
                        fabPlay.setImageResource(android.R.drawable.ic_media_play);
                        onCancel();
                        CLICKED = false;
                        PROGRESS = 0;
                        GET_PROGRESS = 0;
                        progressBar.setProgress(0);
                        POSITION++;
                        bindViewsToSong(positionLogic());
                    }
                }
            }

            @Override
            public void onFinish() {
                bindViewsToSong(positionLogic());
                if (PROGRESS == 100) {
                    if (CLICKED && POSITION == 0) {
                        POSITION++;
                        onFinish();
                    } else if (CLICKED && POSITION > 0) {
                        PROGRESS = 0;
                        GET_PROGRESS = 0;
                        progressBar.setProgress(GET_PROGRESS);
                        POSITION++;
                        cdt.start();
                    }
                } else if(CLICKED) {
                    PROGRESS = 0;
                    GET_PROGRESS = 0;
                    progressBar.setProgress(0);
                    cdt.start();
                } else {
                    PROGRESS = 0;
                    GET_PROGRESS = 0;
                    progressBar.setProgress(0);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        GET_PROGRESS = PROGRESS;
        progressBar.setProgress(GET_PROGRESS);
        bindViewsToSong(positionLogic());
        if (GET_PROGRESS == 0 && CLICKED) {
            fabPlay.setImageResource(android.R.drawable.ic_media_pause);
            cdt.start(); }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        onCancel();
        outState.putInt("POSITION", POSITION);
        outState.putInt("PROGRESS", PROGRESS);
        outState.putInt("GET_PROGRESS", GET_PROGRESS);
        outState.putBoolean("CLICKED", CLICKED);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        POSITION = savedInstanceState.getInt("POSITION");
        PROGRESS = savedInstanceState.getInt("PROGRESS");
        GET_PROGRESS = savedInstanceState.getInt("GET_PROGREgSS");
        CLICKED = savedInstanceState.getBoolean("CLICKED");
        bindViewsToSong(positionLogic());
        if (CLICKED) {
            fabPlay.setImageResource(android.R.drawable.ic_media_pause);
            cdt.start();
            CLICKED = true;
        } else {
            fabPlay.setImageResource(android.R.drawable.ic_media_play);
            CLICKED = false;
        }
    }

    public void onCancel() {
        cdt.cancel();
        GET_PROGRESS = PROGRESS;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_play:
                bindViewsToSong(positionLogic());
                if (!CLICKED) {
                    fabPlay.setImageResource(android.R.drawable.ic_media_pause);
                    cdt.start();
                    CLICKED = true;
                } else {
                    fabPlay.setImageResource(android.R.drawable.ic_media_play);
                    onCancel();
                    CLICKED = false;
                }
                break;

            case R.id.fab_back:
                POSITION--;
                positionLogic();
                cdt.onFinish();
                break;

            case R.id.fab_next:
                POSITION++;
                positionLogic();
                cdt.onFinish();
                break;

            case R.id.button:
                Intent recyclerViewIntent = new Intent(this, RecyclerActivity.class);
                startActivity(recyclerViewIntent);
        }
    }

    public int positionLogic() {
        if (POSITION < 0) {
            POSITION = 99;
        } else if (POSITION > 99) {
            POSITION = 0;
        }
        return POSITION;
    }

    public void bindViewsToSong(int position) {
        songTV.setText(Song.callList()[position].getSongName());
        artistTV.setText(Song.callList()[position].getArtistName());
        albumTV.setText(Song.callList()[position].getAlbumName());
    }
}