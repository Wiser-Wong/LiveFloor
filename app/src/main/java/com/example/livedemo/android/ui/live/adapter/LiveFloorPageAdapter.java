package com.example.livedemo.android.ui.live.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author wangxy
 *
 * 纯净和互动两页适配器
 */
public class LiveFloorPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public LiveFloorPageAdapter(@NonNull FragmentManager fm, int behavior,List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments != null ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    public void detach(){
        if (fragments != null) fragments.clear();
        fragments = null;
    }
}
