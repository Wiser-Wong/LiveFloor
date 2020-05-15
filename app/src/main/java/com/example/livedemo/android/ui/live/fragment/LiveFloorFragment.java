package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.activity.LiveActivity;
import com.example.livedemo.android.ui.live.view.LiveFloorViewPager;
import com.example.livedemo.android.util.CommunicationHelper;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangxy
 *
 *         LiveFloorFragment 直播浮层
 */
public class LiveFloorFragment extends BaseFragment {
	@BindView(R.id.vp_floor) LiveFloorViewPager vpFloor;

	@BindView(R.id.iv_live_close) AppCompatImageView ivLiveClose;

	@BindView(R.id.fl_video_end) FrameLayout flVideoEnd;

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_fragment);
		return builder;
	}

	@Override
	public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this,getFragmentView());
	}

	@Override protected void initData(Bundle savedInstanceState) {
		vpFloor.initPager(this);
	}

	@OnClick(value = R.id.iv_live_close) public void onViewClick(View view){
		if (view.getId() == R.id.iv_live_close) {// 关闭直播
			LiveHelper.getActivityManage().finishActivityClass(LiveActivity.class);
		}
	}

	//设置直播结束
	public void setLiveEnd(boolean isLiveEnd){
		if (isLiveEnd){
			flVideoEnd.setVisibility(View.VISIBLE);
			vpFloor.setVisibility(View.GONE);
			ivLiveClose.setVisibility(View.GONE);
			LiveHelper.display().commitChildReplace(this,R.id.fl_video_end,new LiveVideoEndFragment());
		}else {
			flVideoEnd.setVisibility(View.GONE);
			vpFloor.setVisibility(View.VISIBLE);
			ivLiveClose.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (vpFloor != null) vpFloor.detach();
		vpFloor = null;
	}
}
