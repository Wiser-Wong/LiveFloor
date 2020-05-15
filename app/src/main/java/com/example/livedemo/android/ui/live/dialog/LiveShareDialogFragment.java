package com.example.livedemo.android.ui.live.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.livedemo.R;
import com.example.livedemo.frame.LiveHelper;

/**
 * @author wangxy
 * 
 *         分享弹窗
 */
public class LiveShareDialogFragment extends DialogFragment implements View.OnClickListener {

	private LiveShareDialogBiz biz;

	public static LiveShareDialogFragment createDialog() {
		LiveShareDialogFragment dialog = new LiveShareDialogFragment();
		return dialog;
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.MemberDialogTheme);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (getDialog() != null) {
			// 去除标题栏
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
			// 设置点击空白处是否关闭Dialog
			getDialog().setCanceledOnTouchOutside(true);
			// 设置背景透明 显示弹窗弧度
			if (getDialog().getWindow() != null) {
				getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

				// getDialog().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
				// getDialog().getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility
				// -> {
				// int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
				// //布局位于状态栏下方
				// View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
				// //全屏
				// View.SYSTEM_UI_FLAG_FULLSCREEN |
				// //隐藏导航栏
				// View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
				// View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
				// if (Build.VERSION.SDK_INT >= 19) {
				// uiOptions |= 0x00001000;
				// } else {
				// uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
				// }
				// Objects.requireNonNull(getDialog().getWindow()).getDecorView().setSystemUiVisibility(uiOptions);
				// });
			}
		}

		View view = inflater.inflate(R.layout.live_share_dialog, container, false);

		view.findViewById(R.id.tv_share_weChat).setOnClickListener(this);
		view.findViewById(R.id.tv_share_friend_circle).setOnClickListener(this);

		// 遮挡状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		// view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
		// View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
		// View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		// }

		init();

		return view;
	}

	// 初始化
	private void init() {
		biz = new LiveShareDialogBiz(this);
		initView();
	}

	private void initView() {}

	@Override public void onStart() {
		super.onStart();
		if (getDialog() == null) return;
		Window window = getDialog().getWindow();
		if (window != null && getActivity() != null) {
			WindowManager.LayoutParams wlp = window.getAttributes();
			wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
			wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
			wlp.gravity = Gravity.BOTTOM;
			wlp.windowAnimations = R.style.MemberDialogAnimation;
			window.setAttributes(wlp);
		}
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.tv_share_weChat) {// 微信
		    if (biz != null) biz.shareWeChat();
		    dismiss();
		} else if (id == R.id.tv_share_friend_circle) {// 朋友圈
            if (biz != null) biz.shareFriendCircle();
            dismiss();
        }
	}

	@Override
	public void show(@NonNull FragmentManager manager, @Nullable String tag) {
		if (LiveHelper.display().findFragment(LiveShareDialogFragment.class.getName()) == null)
			super.show(manager, tag);
	}

	@Override public void dismiss() {
		if (biz != null) biz.detach();
		biz = null;
		super.dismiss();
	}

}
