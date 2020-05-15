package com.example.livedemo.android.ui.live.adapter;

import android.view.ViewGroup;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.adapter.holder.barrageholder.LiveBarrageIntoRoomHolder;
import com.example.livedemo.android.ui.live.adapter.holder.barrageholder.LiveBarrageSimpleHolder;
import com.example.livedemo.android.ui.live.adapter.holder.barrageholder.LiveBarrageTipHolder;
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
		switch (type) {
			case LiveBarrageType.CHAT_SYS_TIP://系统第一条消息
				return new LiveBarrageTipHolder(inflate(viewGroup, R.layout.live_floor_barrage_tip_item));
			case LiveBarrageType.CHAT_SIMPLE://普通消息
			case LiveBarrageType.CHAT_SHARE://分享直播间
				return new LiveBarrageSimpleHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));
			case LiveBarrageType.CHAT_INTO_ROOM://进入直播间
				return new LiveBarrageIntoRoomHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));
			default:
				return new LiveBarrageSimpleHolder(inflate(viewGroup, R.layout.live_floor_barrage_item));

		}
	}

}
