package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.livedemo.android.ui.live.adapter.LiveFloorPageAdapter;
import com.example.livedemo.android.ui.live.fragment.LiveFloorPageInteractionFragment;
import com.example.livedemo.android.ui.live.fragment.LiveFloorPagePureFragment;
import com.example.livedemo.android.util.InputHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxy
 *
 * 自定义Floor ViewPager
 */
public class LiveFloorViewPager extends ViewPager {

    private LiveFloorPageAdapter adapter;

    private InputHelper inputHelper;

    public LiveFloorViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public LiveFloorViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        inputHelper = new InputHelper(getContext());

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                inputHelper.hideSoftInput(LiveFloorViewPager.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initPager(Fragment fragment){
        if (fragment == null) return;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LiveFloorPagePureFragment());
        fragments.add(new LiveFloorPageInteractionFragment());

        setAdapter(adapter = new LiveFloorPageAdapter(fragment.getChildFragmentManager(),0,fragments));
        setCurrentItem(1);
    }

    public void detach(){
        if (adapter != null) adapter.detach();
        adapter = null;
        if (inputHelper != null) inputHelper.detach();
        inputHelper = null;
    }
}
