package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<String> numbers = new ArrayList();
    private LinearLayout m_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        m_linearLayout = (LinearLayout) findViewById(R.id.activity_numbers);
        setNumbers();
        printNumbers();
    }

    private void printNumbers() {
        for (String number : numbers) {
            TextView tv = new TextView(this);
            tv.setText(number);
            m_linearLayout.addView(tv);
        }
    }

    private void setNumbers() {
        numbers.add("one");
        numbers.add("two");
        numbers.add("three");
        numbers.add("four");
        numbers.add("five");
        numbers.add("six");
        numbers.add("seven");
        numbers.add("eight");
        numbers.add("nine");
        numbers.add("ten");
    }
}
