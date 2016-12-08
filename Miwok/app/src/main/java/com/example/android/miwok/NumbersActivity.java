package com.example.android.miwok;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.miwok.data.Number;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Number> numbers = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        setNumbers();

        ArrayAdapter<Number> itemsAdapter = new ArrayAdapter<Number>(this, R.layout.miwok_layout, numbers){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View listItemView = convertView;
                if(listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.miwok_layout,parent,false );
                }

                Number number = getItem(position);

                TextView defaultView = (TextView) listItemView.findViewById(R.id.default_text_view);
                defaultView.setText(number.getEnglish());

                TextView miwokView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
                miwokView.setText(number.getMiwok());


                return listItemView;

            }
        };
        ListView listView = (ListView) findViewById(R.id.numbers_list);
        listView.setAdapter(itemsAdapter);
    }

    private void setNumbers() {
        numbers.add(new Number( "one", "lutti"));
        numbers.add(new Number( "two", "ottiko"));
        numbers.add(new Number( "three", "tolookosu"));
        numbers.add(new Number( "four", "oyyisa"));
        numbers.add(new Number( "five", "massokka"));
        numbers.add(new Number( "six", "temmoka"));
        numbers.add(new Number( "seven", "kenekaku"));
        numbers.add(new Number( "eight", "kawinta"));
        numbers.add(new Number( "nine", "wo'e"));
        numbers.add(new Number( "ten", "na'aacha"));
    }
}
