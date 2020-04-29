package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.base.WISERFragment;

import butterknife.ButterKnife;

/**
 * @author wangxy
 *
 *         LiveBottomFunFragment 直播浮层底部状态栏
 */
public class LiveBottomFunFragment extends WISERFragment {

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_bottom_fragment);
		return builder;
	}

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this, getFragmentView());
	}

	@Override protected void initData(Bundle savedInstanceState) {

	}

}
