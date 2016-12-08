package com.example.android.miwok.data;

import android.media.Image;

/**
 * Created by gbans6 on 12/7/2016.
 */

public class Word {
    private String english;
    private String miwok;
    private Integer imageResourceId;

    public Word(String english, String miwok) {
        this.english = english;
        this.miwok = miwok;
    }

    public Word(String english, String miwok, Integer resourceId) {
        this.english = english;
        this.miwok = miwok;
        this.imageResourceId = resourceId;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiwok() {
        return miwok;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId !=null;
    }

}
