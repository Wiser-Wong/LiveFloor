package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedemo.android.ui.live.adapter.LiveBarrageAdapter;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.CustomDefaultItemAnimator;
import com.example.livedemo.android.util.SmoothLayoutManager;
import com.example.livedemo.frame.LiveHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxy
 * 
 *         直播弹幕列表视图
 */
public class LiveBarrageRecycleView extends RecyclerView {

	private LiveBarrageAdapter barrageAdapter;

	private boolean isTouch;

	private final int MAX_COUNT = 10;//最大数量

	private List<LiveBarrageModel> storageList;

	public LiveBarrageRecycleView(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBarrageRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	//初始化
	private void init() {
		setLayoutManager(new SmoothLayoutManager(getContext()));
		setItemAnimator(new DefaultItemAnimator());
		setAdapter(barrageAdapter = new LiveBarrageAdapter(LiveHelper.getActivityManage().getCurrentIsRunningActivity()));
	}

	//初始数据
	public void setData(List<LiveBarrageModel> models) {
		barrageAdapter.setItems(models);
		scrollBottom();
	}

	//添加多条数据
	public void addData(List<LiveBarrageModel> models) {
		if (models == null) return;

		barrageAdapter.addList(models);

		if (!isTouch && !canScrollVertically(1)){
			//检查是否超过固定数量
			checkRemoveList(barrageAdapter.getItemCount() - MAX_COUNT);
			scrollBottom();
		}
	}

	//添加单条数据
	public void addItem(LiveBarrageModel model) {
		barrageAdapter.addItem(barrageAdapter.getItemCount(), model);
		scrollBottom();
	}

	//获取缓存的集合
	public List<LiveBarrageModel> getStorageList() {
		if (storageList == null) storageList = new ArrayList<>();
		return storageList;
	}

	//移除大于最大条时最开始的数据
	public void checkRemoveList(int size){
		if (barrageAdapter.getItemCount() > MAX_COUNT){
			for (int i = 0; i < size; i++) {
				if (barrageAdapter.getItems() != null){
					barrageAdapter.delete(i);
					i--;
					size--;
				}
			}
		}
	}

	//滑动到底部
	public void scrollBottom(){
	    LiveHelper.mainLooper().execute(() -> smoothScrollToPosition(barrageAdapter.getItemCount() - 1));
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				isTouch = true;
				performClick();
				break;
			case MotionEvent.ACTION_UP:
				isTouch = false;
				performClick();
				break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		detach();
	}

	public void detach(){
		if (storageList != null) storageList.clear();
		storageList = null;
		barrageAdapter = null;
	}
}
