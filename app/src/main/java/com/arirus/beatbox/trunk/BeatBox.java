package com.arirus.beatbox.trunk;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by whd910421 on 16/9/29.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private AssetManager mAssetManager;

    public BeatBox(Context context)
    {
        mAssetManager = context.getAssets();
        loadSounds();
    }

    private void loadSounds()
    {
        String [] soundNames;
        try {
            soundNames = mAssetManager.list(SOUND_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + "sounds");
        }catch (IOException ioe)
        {
            Log.i(TAG, "Could not list assets",  ioe);
            return;
        }
    }
}
