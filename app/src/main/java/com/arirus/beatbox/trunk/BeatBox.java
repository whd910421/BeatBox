package com.arirus.beatbox.trunk;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whd910421 on 16/9/29.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private AssetManager mAssetManager;
    private List<Sound> mSounds = new ArrayList<>();


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

        for (String fileName: soundNames)
        {
            String assetsPath = SOUND_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetsPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
