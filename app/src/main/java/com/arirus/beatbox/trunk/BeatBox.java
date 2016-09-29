package com.arirus.beatbox.trunk;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
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
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssetManager;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context)
    {
        mAssetManager = context.getAssets();
//        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            SoundPool.Builder builder = new SoundPool.Builder();
            AudioAttributes.Builder builder1 = new AudioAttributes.Builder();
            mSoundPool = builder.setMaxStreams(5).setAudioAttributes(builder1.setLegacyStreamType(AudioManager.STREAM_MUSIC).build()).build();
        }
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
            Log.e(TAG, "Could not list assets",  ioe);
            return;
        }

        for (String fileName: soundNames)
        {
            try {
                String assetsPath = SOUND_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetsPath);
                load(sound);
                mSounds.add(sound);
            }catch (IOException ioe)
            {
                Log.e(TAG, "Could not load sound" + fileName,  ioe);
            }

        }
    }

    private void load(Sound sound) throws IOException
    {
        AssetFileDescriptor afd = mAssetManager.openFd(sound.getAssetPath());
        int soundID = mSoundPool.load(afd,1);
        sound.setSoundID(soundID);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound sound)
    {
        Integer soundId = sound.getSoundID();
        if (soundId == null) return;
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release()
    {
        mSoundPool.release();
    }
}
