package com.example.android.miwok.data;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

/**
 * Created by gbans6 on 12/8/2016.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private final int m_view_color;

    public WordAdapter(Context context, int resource, List<Word> objects) {
        super(context, resource, objects);
        m_view_color = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.miwok_layout,parent,false );
        }

        View layout = listItemView.findViewById(R.id.linear_layout_tv);
        int color = ContextCompat.getColor(getContext(), m_view_color);
        layout.setBackgroundColor(color);

        Word word = getItem(position);

        TextView defaultView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultView.setText(word.getEnglish());

        TextView miwokView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokView.setText(word.getMiwok());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.miwok_image);
        if (word.hasImage()) {
            imageView.setImageResource(word.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;

    }
}
