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

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        setColors();

        ArrayAdapter<Word> itemsAdapter = new WordAdapter(this, R.color.category_colors, words);

        ListView listView = (ListView) findViewById(R.id.wordsList);
        listView.setAdapter(itemsAdapter);
    }

    private void setColors() {
        words.add(new Word( "red", "weteti", R.drawable.color_red));
        words.add(new Word( "green", "chokoki", R.drawable.color_green));
        words.add(new Word( "brown", "takaakki", R.drawable.color_brown));
        words.add(new Word( "gray", "topoppi", R.drawable.color_gray));
        words.add(new Word( "black", "kululli", R.drawable.color_black));
        words.add(new Word( "white", "keleli", R.drawable.color_white));
        words.add(new Word( "dusty yellow", "topiissa", R.drawable.color_dusty_yellow));
        words.add(new Word( "mustard yellow", "chiwitta", R.drawable.color_mustard_yellow));
    }
}
