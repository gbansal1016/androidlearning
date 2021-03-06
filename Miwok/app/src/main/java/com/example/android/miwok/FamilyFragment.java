package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.miwok.data.Word;
import com.example.android.miwok.data.WordAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private ArrayList<Word> words = new ArrayList();
    private MediaPlayer mediaPlayer;

    private AudioManager am;

    private MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    final AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Pause playback
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };



    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

        setFamilyWords();

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(getActivity(), R.color.category_family, words);

        ListView listView = (ListView) rootView.findViewById(R.id.wordsList);

        listView.setAdapter(itemsAdapter);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                int result = am.requestAudioFocus
                        (afChangeListener,
                                AudioManager.STREAM_MUSIC,
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return rootView;
    }

    private void setFamilyWords() {
        words.add(new Word( "father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word( "mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word( "son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word( "daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word( "older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word( "younger brother", "chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word( "older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word( "younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word( "grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word( "grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
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
        if(am != null) {
            am.abandonAudioFocus(afChangeListener);
        }
    }

}
