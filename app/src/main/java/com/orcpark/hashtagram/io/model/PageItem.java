package com.orcpark.hashtagram.io.model;

import android.support.v4.app.Fragment;
import com.orcpark.hashtagram.ui.ListFragment;

/**
 * Created by orcpark on 2014. 9. 7..
 */
public class PageItem {
    private String hashTag;
    private ListFragment fragment;

    public PageItem() {
    }

    public PageItem(int position, String hashTag) {
        this.hashTag = hashTag;
        fragment = ListFragment.newInstance(position, hashTag);
    }

    public String getHashTag() {
        return hashTag;
    }

    public Fragment getFragment() {
        return fragment;
    }

}
