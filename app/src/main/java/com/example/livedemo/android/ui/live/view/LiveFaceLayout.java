package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.livedemo.R;
import com.example.livedemo.android.util.FaceConversionUtil;
import com.example.livedemo.frame.LiveHelper;

/**
 * @author wangxy
 *
 *         表情布局 + 点
 */
public class LiveFaceLayout extends RelativeLayout {

	private LiveFaceViewPager	faceViewPager;

	private LinearLayout		llFacePoint;

	public LiveFaceLayout(Context context) {
		super(context);
		init();
	}

	public LiveFaceLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.live_face_layout, this, true);
		faceViewPager = findViewById(R.id.vp_chat_face);
		llFacePoint = findViewById(R.id.ll_face_point);

		setBackgroundColor(Color.WHITE);

		LiveHelper.mainLooper().execute(() -> {
			FaceConversionUtil.getInstace().getFileText(getContext());
			faceViewPager.initFace(llFacePoint);
		});
	}

	// 添加输入框View
	public void setChatInputView(AppCompatEditText etChatInput) {
		if (faceViewPager != null) faceViewPager.setChatInputView(etChatInput);
	}

	public void detach() {
		if (faceViewPager != null) faceViewPager.detach();
		faceViewPager = null;
		llFacePoint = null;
	}
}
