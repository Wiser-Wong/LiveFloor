package com.example.livedemo.android.ui.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.shoppingbagholder.LiveShoppingHolder;
import com.example.livedemo.android.ui.live.model.LiveShoppingBagModel;

import java.util.List;

/**
 * @author
 *
 *         LiveShoppingBagAdapter 购物袋中商品适配器
 */
public class LiveShoppingBagAdapter extends RecyclerView.Adapter<LiveShoppingHolder> {

	private Context						context;

	private List<LiveShoppingBagModel>	list;

	public LiveShoppingBagAdapter(Context context) {
		this.context = context;
	}

	public void setItems(List<LiveShoppingBagModel> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@NonNull @Override public LiveShoppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new LiveShoppingHolder(LayoutInflater.from(context).inflate(R.layout.live_shopping_bag_item, parent, false));
	}

	@Override public void onBindViewHolder(@NonNull LiveShoppingHolder holder, int position) {
		if (list != null && list.size() > position) holder.bindData(list.get(position), position);
	}

	@Override public int getItemCount() {
		return list != null ? list.size() : 0;
	}
}
