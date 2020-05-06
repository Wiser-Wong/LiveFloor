package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.example.livedemo.frame.LiveHelper;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.base.WISERFragment;

/**
 * @author wangxy
 *
 *  互动界面
 */
public class LiveFloorPageInteractionFragment extends WISERFragment {

    private LiveFloorTopFragment floorTopFragment;

    @Override
    protected WISERBuilder build(WISERBuilder builder) {
        builder.layoutId(R.layout.live_floor_page_interaction_fragment);
        return builder;
    }

    @Override protected void initData(Bundle savedInstanceState) {
        initPartFragment();
    }

    // 加载分布局
    private void initPartFragment() {
        // 加载浮层顶部区域
        LiveHelper.display().commitChildReplace(this, R.id.fl_floor_top, floorTopFragment = new LiveFloorTopFragment());
        // 加载浮层底部状态栏布局
        LiveHelper.display().commitChildReplace(this, R.id.fl_floor_bottom, LiveFloorBottomFragment.createFloorBottomFragment(isShow -> {
            floorTopFragment.showTopHead(isShow);
        }));
    }

    @Override public void onDetach() {
        super.onDetach();
        floorTopFragment = null;
    }
}
