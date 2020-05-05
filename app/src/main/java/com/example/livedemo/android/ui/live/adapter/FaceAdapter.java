package com.example.livedemo.android.ui.live.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.model.LiveChatEmojiModel;

import java.util.List;

/**
 * @author wangxy
 * 
 *         表情填充器
 */
public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceHolder> {

	private List<LiveChatEmojiModel>	data;

	private LayoutInflater			inflater;

	private OnFaceItemClickListener	onFaceItemClickListener;

	public FaceAdapter(Context context, List<LiveChatEmojiModel> list) {
		this.inflater = LayoutInflater.from(context);
		this.data = list;
	}

	@NonNull @Override public FaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new FaceHolder(inflater.inflate(R.layout.live_chat_face_item, parent, false));
	}

	@Override public void onBindViewHolder(@NonNull FaceHolder holder, int position) {
		LiveChatEmojiModel emoji = data.get(position);
		if (emoji == null) return;
		if (emoji.getId() == R.drawable.chat_face_del_sl) {
			holder.itemView.setBackground(null);
			holder.ivFace.setImageResource(emoji.getId());
		} else if (TextUtils.isEmpty(emoji.getCharacter())) {
			holder.itemView.setBackground(null);
			holder.ivFace.setImageDrawable(null);
		} else {
			holder.ivFace.setImageResource(emoji.getId());
		}

		holder.itemView.setOnClickListener(v -> {
			if (onFaceItemClickListener != null) onFaceItemClickListener.onFaceItemClick(holder.itemView, position);
		});
	}

	public Object getItem(int position) {
		if (data != null && data.size() > position) return data.get(position);
		return null;
	}

	@Override public int getItemCount() {
		return data != null ? data.size() : 0;
	}

	static class FaceHolder extends RecyclerView.ViewHolder {

		AppCompatImageView ivFace;

		FaceHolder(@NonNull View itemView) {
			super(itemView);
			ivFace = itemView.findViewById(R.id.iv_chat_face);
		}
	}

	public void setOnFaceItemClickListener(OnFaceItemClickListener onFaceItemClickListener) {
		this.onFaceItemClickListener = onFaceItemClickListener;
	}

	public interface OnFaceItemClickListener {

		void onFaceItemClick(View view, int position);
	}
}