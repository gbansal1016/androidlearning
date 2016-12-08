package com.example.android.miwok;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.miwok.data.Word;
import com.example.android.miwok.data.WordAdapter;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setFamilyWords();

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_family, words);

        ListView listView = (ListView) findViewById(R.id.wordsList);

        listView.setAdapter(itemsAdapter);
    }

    private void setFamilyWords() {
        words.add(new Word( "father", "әpә", R.drawable.family_father));
        words.add(new Word( "mother", "әṭa", R.drawable.family_mother));
        words.add(new Word( "son", "angsi", R.drawable.family_son));
        words.add(new Word( "daughter", "tune", R.drawable.family_daughter));
        words.add(new Word( "older brother", "taachi", R.drawable.family_older_brother));
        words.add(new Word( "younger brother", "chalitti",R.drawable.family_younger_brother));
        words.add(new Word( "older sister", "teṭe", R.drawable.family_older_sister));
        words.add(new Word( "younger sister", "kolliti", R.drawable.family_younger_sister));
        words.add(new Word( "grandmother", "ama", R.drawable.family_grandmother));
        words.add(new Word( "grandfather", "paapa", R.drawable.family_grandfather));
    }
}
