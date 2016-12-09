package com.example.android.miwok.data;

import android.media.Image;

/**
 * Created by gbans6 on 12/7/2016.
 */

public class Word {
    private String english;
    private String miwok;
    private Integer imageResourceId;
    private Integer audioResourceId;

    public Word(String english, String miwok, Integer resourceId) {
        this.english = english;
        this.miwok = miwok;
        this.audioResourceId = resourceId;
    }

    public Word(String english, String miwok, Integer resourceId, Integer audioResourceId) {
        this.english = english;
        this.miwok = miwok;
        this.imageResourceId = resourceId;
        this.audioResourceId = audioResourceId;
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

    public Integer getAudioResourceId() {
        return audioResourceId;
    }
}
