package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;
import com.example.livedemo.android.view.CenterImageSpan;
import com.wiser.library.util.WISERApp;
import com.wiser.library.util.WISERCheck;

/**
 * @author wangxy
 * 
 *         直播间来人提示布局
 */
public class LiveBarrageIntoRoomTipLayout extends FrameLayout {

	private TextView tvLiveChatContent;

	private LiveBarrageModel model;

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
		tvLiveChatContent = findViewById(R.id.tv_live_chat_content);
		setVisibility(GONE);
	}

	public void intoRoomTipData(LiveBarrageModel model) {
		if (model == null) {
			setVisibility(GONE);
			return;
		}
		this.model = model;

		if (!TextUtils.isEmpty(model.nickName))
			setVisibility(VISIBLE);
		else setVisibility(GONE);
		String nickName = WISERCheck.isEmpty(model.nickName) ? "" : model.nickName;
		SpannableString spannableString = null;
		if (model.type == LiveBarrageType.CHAT_INTO_ROOM) {//进入直播间
			if (model.isVip) {
				spannableString = new SpannableString("icon " + nickName + "  来了");
			} else {
				spannableString = new SpannableString(nickName + "  来了");
			}
		}
		if (spannableString != null) {
			if (model.isVip){
				Drawable drawable = getContext().getResources().getDrawable(R.drawable.live_video_icon_vip);
				drawable.setBounds(0, 0, WISERApp.dip2px(13), WISERApp.dip2px(13));
				spannableString.setSpan(new CenterImageSpan(drawable), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 5, nickName.length() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}else {
				spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 0, nickName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			tvLiveChatContent.setText(spannableString);
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

	public boolean isWandHide(){
		return model == null;
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		tvLiveChatContent = null;
		model = null;
	}
}
