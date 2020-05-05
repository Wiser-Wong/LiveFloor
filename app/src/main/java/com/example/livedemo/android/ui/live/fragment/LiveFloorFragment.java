package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;

/**
 * @author wangxy
 *
 *         LiveFloorFragment 直播浮层
 */
public class LiveFloorFragment extends BaseFragment {

	private LiveFloorTopFragment floorTopFragment;

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_fragment);
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
