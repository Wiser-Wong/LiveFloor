package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;

/**
 * @author wangxy
 *
 *         LiveVideoFragment 直播video
 */
public class LiveVideoFragment extends BaseFragment {

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_video_fragment);
		return builder;
	}

	@Override protected void initData(Bundle savedInstanceState) {

	}
}
