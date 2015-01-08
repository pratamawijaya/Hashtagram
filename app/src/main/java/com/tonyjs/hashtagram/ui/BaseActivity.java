package com.tonyjs.hashtagram.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import com.tonyjs.hashtagram.R;

/**
 * Created by orcpark on 14. 11. 13..
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected Toolbar mToolBar;
    protected Toolbar getToolBar() {
        if (mToolBar == null) {
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
            mToolBar.setTitleTextColor(Color.WHITE);
//            if (mToolBar != null) {
//                setSupportActionBar(mToolBar);
//            }
        }
        return mToolBar;
    }

    public abstract int getContainerResId();

    public void addFragment(int containerResId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerResId, fragment,
                        ((Object)fragment).getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void addFragment(int containerResId, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerResId, fragment, tag)
                .commitAllowingStateLoss();
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainerResId(), fragment,
                        ((Object)fragment).getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void addFragment(Fragment fragment, String stackName) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainerResId(), fragment,
                        ((Object) fragment).getClass().getSimpleName())
                .addToBackStack(stackName)
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerResId(), fragment,
                        ((Object) fragment).getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerResId(), fragment, tag)
                .commitAllowingStateLoss();
    }

    public void popAllBackStack() {
        int max = getSupportFragmentManager().getBackStackEntryCount();
        if (max <= 0) {
            return;
        }

        for (int i = 0; i < max; i++) {
            getSupportFragmentManager().popBackStackImmediate();
        }

    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStackImmediate();
    }

}
