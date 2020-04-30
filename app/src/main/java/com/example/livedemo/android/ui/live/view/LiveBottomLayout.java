package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.activity.LiveActivity;
import com.example.livedemo.android.util.KeyboardHelper;
import com.example.livedemo.frame.LiveHelper;
import com.wiser.library.util.WISERInput;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangxy
 * 
 *         直播底部布局
 */
public class LiveBottomLayout extends FrameLayout {

	@BindView(R.id.rl_floor_bottom) RelativeLayout			rlFloorBottom;

	@BindView(R.id.rl_keyboard_top) RelativeLayout			rlKeyboardTop;

	@BindView(R.id.tv_keyboard_bottom) TextView 			tvKeyboardBottom;

	@BindView(R.id.et_keyboard_top) AppCompatEditText		etKeyboardTop;

	private LiveBarrageRecycleView barrageRecycleView;

	public LiveBottomLayout(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.live_floor_bottom_layout, this, true);
		ButterKnife.bind(this);

		listenerKeyboard();
	}

	// 软键盘监听
	private void listenerKeyboard() {
		KeyboardHelper softKeyboardStateHelper = new KeyboardHelper(getRootView());
		softKeyboardStateHelper.addSoftKeyboardStateListener(new KeyboardHelper.SoftKeyboardStateListener() {

			@Override public void onSoftKeyboardOpened(int keyboardHeightInPx) {
				// 键盘打开
				LiveHelper.toast().show("打开");
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rlKeyboardTop.getLayoutParams();
				params.bottomMargin = keyboardHeightInPx;
				isShowEditTop(true);
			}

			@Override public void onSoftKeyboardClosed() {
				// 键盘关闭
				LiveHelper.toast().show("关闭");
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rlKeyboardTop.getLayoutParams();
				params.bottomMargin = 0;
				isShowEditTop(false);
			}
		});
	}

	// 是否显示软键盘上面的编辑框
	private void isShowEditTop(boolean isShow) {
		LiveHelper.mainLooper().execute(() -> {
			//处理当软键盘显示时，键盘顶部输入框UI展示
			handleKeyboardTop(isShow);
			//处理根布局点击隐藏软键盘
			handleRootViewTouchSoftKeyboardHide(isShow);
			//处理垂直弹幕列表显示在键盘和输入框之上
			handleBarrageListUi(isShow);
		}, 10);
	}

	// 处理当软键盘显示时，键盘顶部输入框UI展示
	private void handleKeyboardTop(boolean isShow){
		if (isShow) {
			rlKeyboardTop.setVisibility(VISIBLE);
			rlFloorBottom.setVisibility(GONE);
			etKeyboardTop.requestFocus();
		} else {
			rlKeyboardTop.setVisibility(GONE);
			rlFloorBottom.setVisibility(VISIBLE);
		}
	}

	// 处理根布局点击隐藏软键盘
	private void handleRootViewTouchSoftKeyboardHide(boolean isShow) {
		if (isShow) {
			getRootView().setOnTouchListener((v, event) -> {
				WISERInput.getInstance().hideSoftInput(etKeyboardTop);
				getRootView().performClick();
				return true;
			});
		} else {
			getRootView().setOnTouchListener(null);
		}
	}

	//处理垂直弹幕当软键盘显示时处于键盘和输入框之上
	private void handleBarrageListUi(boolean isShow){
		if (barrageRecycleView == null) return;
		ViewGroup.MarginLayoutParams barrageParams = (ViewGroup.MarginLayoutParams) barrageRecycleView.getLayoutParams();
		ViewGroup.MarginLayoutParams inputParams = (ViewGroup.MarginLayoutParams) rlKeyboardTop.getLayoutParams();
		if (isShow) {
			barrageParams.bottomMargin = inputParams.bottomMargin + inputParams.height;
		} else {
			barrageParams.bottomMargin = rlFloorBottom.getHeight();
		}
	}

	@OnClick(value = { R.id.iv_live_close, R.id.iv_live_send,R.id.tv_keyboard_bottom }) public void onViewClick(View v) {
		switch (v.getId()) {
			case R.id.iv_live_close:// 关闭直播
				LiveHelper.getActivityManage().finishActivityClass(LiveActivity.class);
				break;
			case R.id.iv_live_send:// 发送消息
				break;
			case R.id.tv_keyboard_bottom://底部点击弹窗键盘
				etKeyboardTop.setFocusable(true);
				etKeyboardTop.setFocusableInTouchMode(true);
				etKeyboardTop.requestFocus();
				WISERInput.getInstance().showSoftInput(etKeyboardTop);
				break;
		}
	}

	public void setBarrageRecycleView(LiveBarrageRecycleView barrageRecycleView) {
		this.barrageRecycleView = barrageRecycleView;
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		detach();
	}

	//清理
	public void detach(){
		rlFloorBottom = null;
		rlKeyboardTop = null;
		tvKeyboardBottom = null;
		etKeyboardTop = null;
		if (barrageRecycleView != null) barrageRecycleView.detach();
		barrageRecycleView = null;
	}
}
