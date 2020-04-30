package com.example.livedemo.android.ui.live.adapter;

import android.view.ViewGroup;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.barrageholder.BaseHolder;
import com.example.livedemo.android.ui.live.adapter.barrageholder.LiveBarrageHolder;
import com.example.livedemo.android.ui.live.adapter.barrageholder.LiveBarrageTipHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;
import com.wiser.library.adapter.WISERRVAdapter;
import com.wiser.library.base.WISERActivity;

/**
 * @author
 *
 *         LiveBarrageAdapter 弹幕适配器
 */
public class LiveBarrageAdapter extends WISERRVAdapter<LiveBarrageModel, BaseHolder> {

	public LiveBarrageAdapter(WISERActivity wiserActivity) {
		super(wiserActivity);
	}

	@Override
	public int getItemViewType(int position) {
		if (getItem(position) != null) return getItem(position).type;
		return super.getItemViewType(position);
	}

	@Override public BaseHolder newViewHolder(ViewGroup viewGroup, int type) {
		switch (type){
			case LiveBarrageType.CHAT_TIP:
				return new LiveBarrageTipHolder(inflate(viewGroup, R.layout.live_floor_barrage_tip_item));
			case LiveBarrageType.CHAT_VIP:
				return new LiveBarrageHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));
			default:
				return new LiveBarrageHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));

		}
	}

}
