package com.example.livedemo.android.ui.live.adapter.holder.barrageholder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.wiser.library.util.WISERApp;

import butterknife.BindView;

/**
 * @author wangxy
 * 
 *         弹幕holder
 */
public class LiveBarrageHolder extends BaseHolder<LiveBarrageModel> {

	@BindView(R.id.tv_name) TextView tvName;

	public LiveBarrageHolder(@NonNull View itemView) {
		super(itemView);
	}

	@Override public void bindData(LiveBarrageModel model, int position) {
		if (model == null) return;
		if (position == adapter().getItemCount() - 1 && model.isHide) {
			itemView.setVisibility(View.INVISIBLE);
			tvName.setVisibility(View.INVISIBLE);
		}
		else {
			itemView.setVisibility(View.VISIBLE);
			tvName.setVisibility(View.VISIBLE);
		}

		SpannableString spannableString = new SpannableString("icon"+model.nickName + ":" +model.content);
		Drawable drawable = getContext().getResources().getDrawable(R.drawable.emoji_kf_1);
		drawable.setBounds(0, 0, WISERApp.dip2px(17), WISERApp.dip2px(17));
		spannableString.setSpan(new ImageSpan(drawable), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW), 4,model.nickName.length() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvName.setText(spannableString);

	}
}