package com.example.android.miwok;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.miwok.data.Word;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setFamilyWords();

        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.miwok_layout, words){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View listItemView = convertView;
                if(listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.miwok_layout,parent,false );
                }

                Word word = getItem(position);

                TextView defaultView = (TextView) listItemView.findViewById(R.id.default_text_view);
                defaultView.setText(word.getEnglish());

                TextView miwokView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
                miwokView.setText(word.getMiwok());

                return listItemView;

            }
        };

        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);
    }

    private void setFamilyWords() {
        words.add(new Word( "father", "әpә"));
        words.add(new Word( "mother", "әṭa"));
        words.add(new Word( "son", "angsi"));
        words.add(new Word( "daughter", "tune"));
        words.add(new Word( "older brother", "taachi"));
        words.add(new Word( "younger brother", "chalitti"));
        words.add(new Word( "older sister", "teṭe"));
        words.add(new Word( "younger sister", "kolliti"));
        words.add(new Word( "grandmother", "ama"));
        words.add(new Word( "grandfather", "paapa"));
    }
}
