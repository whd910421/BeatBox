package com.arirus.beatbox.trunk;

import android.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.NewInstance();
    }
}
