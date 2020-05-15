package com.example.livedemo.android.ui.live.adapter.holder.barrageholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;

/**
 * @author wangxy
 * 
 *         弹幕holder
 */
public class LiveBarrageTipHolder extends BaseHolder<LiveBarrageModel> {

	private TextView tvLiveChatContent;

	public LiveBarrageTipHolder(@NonNull View itemView) {
		super(itemView);
		tvLiveChatContent = itemView.findViewById(R.id.tv_live_chat_content);
	}

	@Override
    public void bindData(LiveBarrageModel model, int position) {
		if (model == null) return;
		tvLiveChatContent.setText(model.content);
	}
}