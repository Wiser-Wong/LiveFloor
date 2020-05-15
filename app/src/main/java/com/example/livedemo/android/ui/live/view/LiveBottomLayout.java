package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.fragment.LiveFloorBottomFragment;
import com.example.livedemo.android.ui.live.dialog.LiveShareDialogFragment;
import com.example.livedemo.android.ui.live.dialog.LiveShoppingBagDialogFragment;
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

	@BindView(R.id.rl_floor_bottom) RelativeLayout		rlFloorBottom;

	@BindView(R.id.rl_keyboard_top) RelativeLayout		rlKeyboardTop;

	@BindView(R.id.tv_keyboard_bottom) TextView			tvKeyboardBottom;

	@BindView(R.id.et_keyboard_top) AppCompatEditText	etKeyboardTop;

	@BindView(R.id.iv_live_face) AppCompatImageView		ivFace;

	@BindView(R.id.iv_live_keyboard) AppCompatImageView	ivKeyboard;

	@BindView(R.id.face_view) LiveFaceLayout			faceLayout;

	public static final int								MAX_INPUT_LIMIT	= 60;

	private LiveFloorBottomFragment.OnKeyboardListener	onKeyboardListener;

	private boolean										isFaceShow;				// 是否表情显示

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

		// 为了软键盘中将换行键修改成发送按钮并且还可以自动换行需要配置下面属性并在xml中AppCompatEditText控件增加android:imeOptions="actionSend"属性
		etKeyboardTop.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		etKeyboardTop.setSingleLine(false);
		etKeyboardTop.setHorizontallyScrolling(false);

		faceLayout.setChatInputView(etKeyboardTop);

		listenerKeyboard();
		listenerEditTextInputLimit();
		listenerKeyboardSend();
	}

	@Override public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		if (hasWindowFocus) listenerRootTouch();
	}

	// 根布局按下监听
	private void listenerRootTouch() {
		((ViewGroup) getParent()).setOnTouchListener((v, event) -> {
			WISERInput.getInstance().hideSoftInput(etKeyboardTop);
			((ViewGroup) getParent()).performClick();
			handleFace(false);
			this.isFaceShow = false;
			return false;
		});
	}

	// 软键盘监听
	private void listenerKeyboard() {
		KeyboardHelper softKeyboardStateHelper = new KeyboardHelper(getRootView());
		softKeyboardStateHelper.addSoftKeyboardStateListener(new KeyboardHelper.SoftKeyboardStateListener() {

			@Override public void onSoftKeyboardOpened(int keyboardHeightInPx) {
				//不使用adjustPan的原因是会将整个布局都整体上移，其他层级的布局也会变化，例如视频播放
//				((FragmentActivity)getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
				//使用线性布局 会出现弹出软键盘 如果marginBottom 大于所剩的布局的情况，会无法将输入框顶到软键盘上面，所以选择了父布局为RelativeLayout来解决无法将布局顶出屏幕
				if (onKeyboardListener != null) onKeyboardListener.onKeyboardState(true);
				// 键盘打开
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rlKeyboardTop.getLayoutParams();
				params.bottomMargin = keyboardHeightInPx;
				isShowEditTop(true);
			}

			@Override public void onSoftKeyboardClosed() {
				if (onKeyboardListener != null) onKeyboardListener.onKeyboardState(false);
				// 键盘关闭
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rlKeyboardTop.getLayoutParams();
				params.bottomMargin = 0;
				isShowEditTop(false);
			}
		});
	}

	// 软键盘发送事件监听
	private void listenerKeyboardSend() {
//		etKeyboardTop.setOnKeyListener((v, keyCode, event) -> {
//			if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_UP == event.getAction()) {
//				// 处理事件
//				handleSend();
//				return true;
//			}
//			return false;
//		});
	}

	public void setOnKeyboardListener(LiveFloorBottomFragment.OnKeyboardListener onKeyboardListener) {
		this.onKeyboardListener = onKeyboardListener;
	}

	// 监听输入字数限制
	private void listenerEditTextInputLimit() {
		etKeyboardTop.addTextChangedListener(new TextWatcher() {

			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override public void afterTextChanged(Editable s) {
				if (!TextUtils.isEmpty(s.toString()) && s.length() > MAX_INPUT_LIMIT) {
					LiveHelper.toast().show("限制" + MAX_INPUT_LIMIT + "个字符。");
					if (etKeyboardTop.getText() != null) etKeyboardTop.getText().delete(MAX_INPUT_LIMIT, s.length());
					etKeyboardTop.setSelection(etKeyboardTop.length());
				}
			}
		});
	}

	// 是否显示软键盘上面的编辑框
	private void isShowEditTop(boolean isShow) {
		LiveHelper.mainLooper().execute(() -> {
			// 处理当软键盘显示时，键盘顶部输入框UI展示
			handleKeyboardTop(isShow);
		}, 10);
	}

	// 处理当软键盘显示时，键盘顶部输入框UI展示
	private void handleKeyboardTop(boolean isShow) {
		if (isShow) {
			faceLayout.setVisibility(GONE);
			rlKeyboardTop.setVisibility(VISIBLE);
			rlFloorBottom.setVisibility(GONE);
			etKeyboardTop.requestFocus();
			// 显示键盘时恢复表情icon
			handleFaceIcon(true);
		} else {
			handleFace(this.isFaceShow);
		}
	}

	// 处理表情
	private void handleFace(boolean isShow) {
		if (isShow) {
			if (faceLayout.getVisibility() == GONE) faceLayout.setVisibility(VISIBLE);
			if (rlKeyboardTop.getVisibility() == GONE) rlKeyboardTop.setVisibility(VISIBLE);
			if (rlFloorBottom.getVisibility() == VISIBLE) rlFloorBottom.setVisibility(GONE);
		} else {
			if (faceLayout.getVisibility() == VISIBLE) faceLayout.setVisibility(GONE);
			if (rlFloorBottom.getVisibility() == GONE) rlFloorBottom.setVisibility(VISIBLE);
			if (rlKeyboardTop.getVisibility() == VISIBLE) rlKeyboardTop.setVisibility(GONE);
		}
		handleFaceIcon(!isShow);
	}

	// 处理表情icon
	private void handleFaceIcon(boolean isShow) {
		if (isShow) {
			if (ivKeyboard.getVisibility() == VISIBLE) ivKeyboard.setVisibility(GONE);
			if (ivFace.getVisibility() == GONE) ivFace.setVisibility(VISIBLE);
		} else {
			if (ivKeyboard.getVisibility() == GONE) ivKeyboard.setVisibility(VISIBLE);
			if (ivFace.getVisibility() == VISIBLE) ivFace.setVisibility(GONE);
		}
	}

	@OnClick(value = {R.id.iv_live_face, R.id.iv_live_keyboard, R.id.iv_live_send, R.id.tv_keyboard_bottom, R.id.iv_floor_share,
			R.id.iv_floor_shopping_bag }) public void onViewClick(View v) {
		switch (v.getId()) {
			case R.id.iv_live_face:// 表情
				isFaceShow = true;
				WISERInput.getInstance().hideSoftInput(etKeyboardTop);
				break;
			case R.id.iv_live_keyboard:// 键盘
				this.isFaceShow = false;
				WISERInput.getInstance().showSoftInput(etKeyboardTop);
				break;
			case R.id.iv_live_send:// 发送消息
				handleSend();
				break;
			case R.id.tv_keyboard_bottom:// 底部点击弹窗键盘
				etKeyboardTop.setFocusable(true);
				etKeyboardTop.setFocusableInTouchMode(true);
				etKeyboardTop.requestFocus();
				WISERInput.getInstance().showSoftInput(etKeyboardTop);
				break;
			case R.id.iv_floor_share:// 分享弹窗
				LiveShareDialogFragment.createDialog().show(LiveHelper.getActivityManage().getCurrentIsRunningActivity().getSupportFragmentManager(), LiveShareDialogFragment.class.getName());
				break;
			case R.id.iv_floor_shopping_bag:// 购物袋
				LiveShoppingBagDialogFragment.createDialog().show(LiveHelper.getActivityManage().getCurrentIsRunningActivity().getSupportFragmentManager(),
						LiveShoppingBagDialogFragment.class.getName());
				break;
		}
	}

	// 发送处理
	private void handleSend() {
		if (etKeyboardTop.length() == 0) return;
		// this.isFaceShow = false;
		// WISERInput.getInstance().hideSoftInput(etKeyboardTop);
		etKeyboardTop.setText("");
		// handleFace(false);
	}

	// 禁言
	public void setShutUp(boolean isShutUp) {
		if (isShutUp) {
			tvKeyboardBottom.setEnabled(false);
			tvKeyboardBottom.setText("你已被禁言");
			tvKeyboardBottom.setTextColor(Color.parseColor("#50ffffff"));
		} else {
			tvKeyboardBottom.setEnabled(true);
			tvKeyboardBottom.setText("说点什么...");
			tvKeyboardBottom.setTextColor(Color.WHITE);
		}
	}

	@Override protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		detach();
	}

	// 清理
	public void detach() {
		if (faceLayout != null) faceLayout.detach();
		faceLayout = null;
		rlFloorBottom = null;
		rlKeyboardTop = null;
		tvKeyboardBottom = null;
		etKeyboardTop = null;
		ivKeyboard = null;
		ivFace = null;
		onKeyboardListener = null;
	}
}
