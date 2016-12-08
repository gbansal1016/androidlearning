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

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setNumbers();

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

    private void setNumbers() {
        words.add(new Word( "one", "lutti"));
        words.add(new Word( "two", "ottiko"));
        words.add(new Word( "three", "tolookosu"));
        words.add(new Word( "four", "oyyisa"));
        words.add(new Word( "five", "massokka"));
        words.add(new Word( "six", "temmoka"));
        words.add(new Word( "seven", "kenekaku"));
        words.add(new Word( "eight", "kawinta"));
        words.add(new Word( "nine", "wo'e"));
        words.add(new Word( "ten", "na'aacha"));
    }
}
