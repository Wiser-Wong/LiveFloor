package com.example.livedemo.android.ui.live.adapter.barrageholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.wiser.library.adapter.WISERHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

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
		tvName.setText(model.name);
	}
}