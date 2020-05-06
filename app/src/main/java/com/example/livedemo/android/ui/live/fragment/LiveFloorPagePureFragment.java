package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.base.WISERFragment;

/**
 * @author wangxy
 *
 *  纯净界面
 */
public class LiveFloorPagePureFragment extends WISERFragment {

    @Override
    protected WISERBuilder build(WISERBuilder builder) {
        builder.layoutId(R.layout.live_floor_page_pure_fragment);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
