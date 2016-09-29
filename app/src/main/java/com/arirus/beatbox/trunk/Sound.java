package com.arirus.beatbox.trunk;

/**
 * Created by whd910421 on 16/9/29.
 */

public class Sound {
    private String mAssetPath;
    private String mName;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String [] components = assetPath.split("/");
        String fileName = components[components.length-1];

        mName = fileName.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
