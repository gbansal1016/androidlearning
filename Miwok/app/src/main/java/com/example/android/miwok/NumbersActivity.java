package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.miwok.data.Word;
import com.example.android.miwok.data.WordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private final ArrayList<Word> words = new ArrayList();

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setNumbers();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_numbers, words);
        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void setNumbers() {
        words.add(new Word( "one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word( "two", "ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word( "three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word( "four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word( "five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word( "six", "temmoka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word( "seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word( "eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word( "nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word( "ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
