package com.example.livedemo.android.ui.live.activity;

import android.content.Intent;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.fragment.LiveFloorFragment;
import com.example.livedemo.android.ui.live.fragment.LiveVideoFragment;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseActivity;
import com.wiser.library.base.WISERBuilder;

/**
 * @author wangxy
 *
 *         直播界面
 */
public class LiveActivity extends BaseActivity {

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_activity);
		builder.setStatusBarFullTransparent();
		return builder;
	}

	@Override protected void initData(Intent intent) {
		LiveHelper.display().commitReplace(R.id.fl_video, new LiveVideoFragment(), LiveVideoFragment.class.getName());
		LiveHelper.display().commitReplace(R.id.fl_floor, new LiveFloorFragment(), LiveFloorFragment.class.getName());
	}
}
