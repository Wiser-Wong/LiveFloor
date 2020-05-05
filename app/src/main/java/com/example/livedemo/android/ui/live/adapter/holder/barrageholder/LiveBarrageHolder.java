package com.example.livedemo.android.ui.live.adapter.holder.barrageholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;

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
		tvName.setText(model.name);
	}
}