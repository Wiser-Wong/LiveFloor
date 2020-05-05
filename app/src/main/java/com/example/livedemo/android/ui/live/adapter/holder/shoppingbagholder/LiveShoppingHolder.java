package com.example.livedemo.android.ui.live.adapter.holder.shoppingbagholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.model.LiveShoppingBagModel;

/**
 * @author wangxy
 * 
 *         商品购物袋holder
 */
public class LiveShoppingHolder extends BaseHolder<LiveShoppingBagModel> {

	public LiveShoppingHolder(@NonNull View itemView) {
		super(itemView);
	}

	@Override public void bindData(LiveShoppingBagModel model, int position) {
		if (model == null) return;
	}
}