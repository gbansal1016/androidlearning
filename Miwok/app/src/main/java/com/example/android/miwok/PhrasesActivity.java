package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.miwok.data.Word;
import com.example.android.miwok.data.WordAdapter;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    private ArrayList<Word> words = new ArrayList();

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

        setFamilyWords();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_phrases, words);
        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMediaPlayer();

                Word word = words.get(position);
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFamilyWords() {
        words.add(new Word( "Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word( "What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word( "My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word( "How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word( "I’m feeling good", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word( "Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word( "Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word( "I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word( "Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word( "Come here.", "әnni'nemF", R.raw.phrase_come_here));
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
