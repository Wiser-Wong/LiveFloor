package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.ui.live.view.LiveBarrageLayout;
import com.example.livedemo.android.ui.live.view.LiveBottomLayout;
import com.example.livedemo.frame.LiveHelper;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangxy
 *
 *         LiveBottomFunFragment 直播浮层底部状态栏
 */
public class LiveFloorBottomFragment extends BaseFragment<LiveFloorBottomBiz>  implements ILiveVideoFloorBottomView, ILiveVideoFloorMessageView{

	@BindView(R.id.live_barrage) LiveBarrageLayout	barrageLayout;

	@BindView(R.id.live_bottom) LiveBottomLayout	liveBottom;

	private OnKeyboardListener						onKeyboardListener;

	public static LiveFloorBottomFragment createFloorBottomFragment(OnKeyboardListener onKeyboardListener) {
		LiveFloorBottomFragment fragment = new LiveFloorBottomFragment();
		fragment.setOnKeyboardListener(onKeyboardListener);
		return fragment;
	}

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_bottom_fragment);
		return builder;
	}

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this, getFragmentView());
	}

	@Override protected void initData(Bundle savedInstanceState) {

		barrageLayout.initMessages(biz().firstData());

		liveBottom.setOnKeyboardListener(onKeyboardListener);

		// 测试定时添加多条数据
		LiveHelper.mainLooper().execute(runnable, 1300);

		// 测试直播用户进入信息
		LiveHelper.mainLooper().execute(runnable1, 500);
	}

	private Runnable	runnable	= new Runnable() {

										@Override public void run() {
											barrageLayout.addMessages(biz().addData());
											LiveHelper.mainLooper().execute(runnable, 1300);
										}
									};

	private Runnable	runnable1	= new Runnable() {

										@Override public void run() {
											barrageLayout.addTipMessage(biz().addItem());
											LiveHelper.mainLooper().execute(runnable1, 500);
										}
									};

	@Override public void onPause() {
		super.onPause();
		LiveHelper.log().e("onPause");
		if (barrageLayout != null) barrageLayout.setPause(true);
	}

	@Override public void onResume() {
		super.onResume();
		LiveHelper.log().e("onResume");
		if (barrageLayout != null) barrageLayout.setPause(false);
	}

	@Override public void onDestroy() {
		super.onDestroy();
		LiveHelper.mainLooper().getHandler().removeCallbacks(runnable);
		LiveHelper.mainLooper().getHandler().removeCallbacks(runnable1);

		if (liveBottom != null) liveBottom.detach();
		liveBottom = null;
		if (barrageLayout != null) barrageLayout.detach();
		barrageLayout = null;
		onKeyboardListener = null;
	}

	private void setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
		this.onKeyboardListener = onKeyboardListener;
	}

	@Override
	public void setShutUp(boolean isShutUp) {
		if (liveBottom != null) liveBottom.setShutUp(isShutUp);
	}

	@Override
	public void initMessages(List<LiveBarrageModel> barrageModels) {
		if (barrageLayout != null) barrageLayout.initMessages(barrageModels);
	}

	@Override
	public void initMessage(LiveBarrageModel barrageModel) {
		if (barrageLayout != null) barrageLayout.initMessage(barrageModel);
	}

	@Override
	public void addMessages(List<LiveBarrageModel> barrageModels) {
		if (barrageLayout != null) barrageLayout.addMessages(barrageModels);
	}

	@Override
	public void addMessage(LiveBarrageModel barrageModel) {
		if (barrageLayout != null) barrageLayout.addMessage(barrageModel);
	}

	@Override
	public void addTipMessage(LiveBarrageModel barrageModel) {
		if (barrageLayout != null) barrageLayout.addTipMessage(barrageModel);
	}

	public interface OnKeyboardListener {

		void onKeyboardState(boolean isShow);
	}
}
