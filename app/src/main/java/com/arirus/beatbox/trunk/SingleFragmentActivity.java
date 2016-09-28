package com.arirus.beatbox.trunk;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by whd910421 on 16/7/26.
 */
/*
* 一个抽象基类
* */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_crime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null)
        {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
