package com.example.livedemo.android.ui;

import android.content.Intent;
import android.view.View;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.activity.LiveActivity;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseActivity;
import com.wiser.library.base.WISERBuilder;

public class HomeActivity extends BaseActivity {

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.home_activity);
		return builder;
	}

	@Override protected void initData(Intent intent) {

	}

	public void jumpLive(View view) {
		LiveHelper.display().intent(LiveActivity.class);
	}
}
