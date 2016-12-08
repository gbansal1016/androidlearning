package com.example.android.miwok.data;

/**
 * Created by gbans6 on 12/7/2016.
 */

public class Word {
    private String english;
    private String miwok;

    public Word(String english, String miwok) {
        this.english = english;
        this.miwok = miwok;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiwok() {
        return miwok;
    }
}
