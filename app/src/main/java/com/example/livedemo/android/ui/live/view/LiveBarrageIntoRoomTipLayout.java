package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;

/**
 * @author wangxy
 * 
 *         直播间来人提示布局
 */
public class LiveBarrageIntoRoomTipLayout extends FrameLayout {

	private TextView tvName;

	public LiveBarrageIntoRoomTipLayout(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBarrageIntoRoomTipLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.live_floor_barrage_item, this, true);
		tvName = findViewById(R.id.tv_name);
	}

	public void intoRoomTipData(LiveBarrageModel model) {
		if (model == null) {
			setVisibility(GONE);
			return;
		}
		if (!TextUtils.isEmpty(model.name)) {
			setVisibility(VISIBLE);
			tvName.setText(model.name);
		} else {
			setVisibility(GONE);
			tvName.setText("");
		}
	}

	// 设置隐藏状态
	public void setHideUi(boolean isHide) {
		if (isHide) {
			if (getVisibility() == VISIBLE) setVisibility(GONE);
		} else {
			if (getVisibility() == GONE) setVisibility(VISIBLE);
		}
	}

}
