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

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setNumbers();

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_numbers, words);
        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);
    }

    private void setNumbers() {
        words.add(new Word( "one", "lutti", R.drawable.number_one));
        words.add(new Word( "two", "ottiko", R.drawable.number_two));
        words.add(new Word( "three", "tolookosu", R.drawable.number_three));
        words.add(new Word( "four", "oyyisa", R.drawable.number_four));
        words.add(new Word( "five", "massokka", R.drawable.number_five));
        words.add(new Word( "six", "temmoka", R.drawable.number_six));
        words.add(new Word( "seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word( "eight", "kawinta", R.drawable.number_eight));
        words.add(new Word( "nine", "wo'e", R.drawable.number_nine));
        words.add(new Word( "ten", "na'aacha", R.drawable.number_ten));
    }
}
