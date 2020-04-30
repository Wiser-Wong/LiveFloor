package com.example.livedemo.android.ui.live.adapter.barrageholder;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.wiser.library.adapter.WISERHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangxy
 * 
 *         弹幕holder
 */
public class LiveBarrageTipHolder extends BaseHolder<LiveBarrageModel> {

	@BindView(R.id.tv_name) TextView tvName;

	public LiveBarrageTipHolder(@NonNull View itemView) {
		super(itemView);
	}

	@Override public void bindData(LiveBarrageModel model, int position) {
		if (model == null) return;
		tvName.setText(model.name);
	}
}