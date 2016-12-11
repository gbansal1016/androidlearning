package com.example.android.soundproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.Kalimba);

        final Button play = (Button) findViewById(R.id.play_bv);

        play.setEnabled(true);

        final Button pause = (Button) findViewById(R.id.pause_bv);
        pause.setEnabled(false);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.setEnabled(false);
                pause.setEnabled(true);
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.setEnabled(true);
                pause.setEnabled(false);

                mediaPlayer.pause();
            }
        });
    }
}
