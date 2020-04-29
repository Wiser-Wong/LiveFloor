package com.example.livedemo.android.ui.live.adapter;

import android.view.ViewGroup;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.barrageholder.LiveBarrageHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.wiser.library.adapter.WISERRVAdapter;
import com.wiser.library.base.WISERActivity;

/**
 * @author
 *
 *         LiveBarrageAdapter 弹幕适配器
 */
public class LiveBarrageAdapter extends WISERRVAdapter<LiveBarrageModel, LiveBarrageHolder> {

	public LiveBarrageAdapter(WISERActivity wiserActivity) {
		super(wiserActivity);
	}

	@Override public LiveBarrageHolder newViewHolder(ViewGroup viewGroup, int type) {
		return new LiveBarrageHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));
	}

}
