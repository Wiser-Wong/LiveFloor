package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedemo.android.ui.live.adapter.LiveBarrageAdapter;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.frame.LiveHelper;

import java.util.List;

/**
 * @author wangxy
 * 
 *         直播弹幕列表视图
 */
public class LiveBarrageRecycleView extends RecyclerView {

	private LiveBarrageAdapter barrageAdapter;

	public LiveBarrageRecycleView(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBarrageRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setLayoutManager(new LinearLayoutManager(getContext()));
		setAdapter(barrageAdapter = new LiveBarrageAdapter(LiveHelper.getActivityManage().getCurrentIsRunningActivity()));
	}

	//初始数据
	public void setData(List<LiveBarrageModel> models) {
		barrageAdapter.setItems(models);
	}

	//添加多条数据
	public void addData(List<LiveBarrageModel> models) {
		barrageAdapter.addList(models);
	}

	//添加单条数据
	public void addItem(LiveBarrageModel model) {
		barrageAdapter.addItem(barrageAdapter.getItemCount() - 1, model);
	}
}
