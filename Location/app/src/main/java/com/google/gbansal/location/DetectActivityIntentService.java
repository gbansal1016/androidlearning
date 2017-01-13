package com.google.gbansal.location;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by gbans6 on 12/18/2016.
 */

public class DetectActivityIntentService extends IntentService {

    private static final String TAG = DetectActivityIntentService.class.getSimpleName();
    public DetectActivityIntentService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
