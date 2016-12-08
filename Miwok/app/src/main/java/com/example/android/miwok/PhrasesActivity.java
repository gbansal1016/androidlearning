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

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setFamilyWords();

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_phrases, words);
        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);
    }

    private void setFamilyWords() {
        words.add(new Word( "Where are you going?", "minto wuksus"));
        words.add(new Word( "What is your name?", "tinnә oyaase'nә"));
        words.add(new Word( "My name is...", "oyaaset..."));
        words.add(new Word( "How are you feeling?", "michәksәs?"));
        words.add(new Word( "I’m feeling good", "kuchi achit"));
        words.add(new Word( "Are you coming?", "әәnәs'aa?"));
        words.add(new Word( "Yes, I’m coming.", "hәә’ әәnәm"));
        words.add(new Word( "I’m coming.", "әәnәm"));
        words.add(new Word( "Let’s go.", "yoowutis"));
        words.add(new Word( "Come here.", "әnni'nemF"));
    }
}
