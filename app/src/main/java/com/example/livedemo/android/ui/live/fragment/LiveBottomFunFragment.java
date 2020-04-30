package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.view.LiveBarrageRecycleView;
import com.example.livedemo.android.ui.live.view.LiveBottomLayout;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangxy
 *
 *         LiveBottomFunFragment 直播浮层底部状态栏
 */
public class LiveBottomFunFragment extends BaseFragment<LiveBottomFunBiz> {

	@BindView(R.id.rlv_barrage) LiveBarrageRecycleView rlvBarrage;

	@BindView(R.id.live_bottom) LiveBottomLayout 	   liveBottom;

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_bottom_fragment);
		return builder;
	}

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this, getFragmentView());
	}

	@Override protected void initData(Bundle savedInstanceState) {
		liveBottom.setBarrageRecycleView(rlvBarrage);

		rlvBarrage.setData(biz().firstData());

		LiveHelper.mainLooper().execute(runnable,2000);
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			rlvBarrage.addData(biz().addData());
			LiveHelper.mainLooper().execute(runnable,2000);
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		LiveHelper.mainLooper().getHandler().removeCallbacks(runnable);

		if (liveBottom != null) liveBottom.detach();
		liveBottom = null;
		if (rlvBarrage != null) rlvBarrage.detach();
		rlvBarrage = null;
	}
}
