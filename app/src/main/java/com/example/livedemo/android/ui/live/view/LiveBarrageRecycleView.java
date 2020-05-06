package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedemo.android.ui.live.adapter.LiveBarrageAdapter;
import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorMessageView;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.SmoothLayoutManager;
import com.example.livedemo.frame.LiveHelper;
import com.wiser.library.util.WISERInput;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxy
 * 
 *         直播弹幕列表视图
 */
public class LiveBarrageRecycleView extends RecyclerView implements ILiveVideoFloorMessageView {

	private LiveBarrageAdapter		barrageAdapter;

	private boolean					isTouch;					// 是否触摸聊天列表

	private final int				MAX_COUNT			= 100;	// 最大数量

	private final int				NEAR_BOTTOM_COUNT	= 1;	// 滚动到接近底部然后顺滑滚动

	private List<LiveBarrageModel>	storageList;				// 缓存滑动或者触摸聊天列表时并且没有处于最底部的时候记录存储，等没有触摸并且处于最底部的时候讲缓存数据添加进聊天列表

	private List<LiveBarrageModel> 	models = new ArrayList<>(); //记录新增消息

	private OnScrollMessageListener	onScrollMessageListener;

	private boolean					isPause;					// 是否暂停

	private LiveBarrageModel		intoRoomTipModel;			// 记录进入直播间数据

	private boolean					isScrollBottom;				// 是否滑动到底部

	public LiveBarrageRecycleView(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBarrageRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	// 初始化
	private void init() {
		setLayoutManager(new SmoothLayoutManager(getContext()));
		setItemAnimator(null);// 去掉动画要不删除最后一条的时候滚动到底会影响滚动
		setAdapter(barrageAdapter = new LiveBarrageAdapter(LiveHelper.getActivityManage().getCurrentIsRunningActivity()));
	}

	// 初始消息数据
	@Override
	public void initMessages(List<LiveBarrageModel> models) {
		if (models == null) return;
		barrageAdapter.setItems(models);
		scrollBottom();
	}

	// 初始消息数据
	@Override
	public void initMessage(LiveBarrageModel model) {
		if (model == null) return;

		resetModels();
		models.add(model);

		barrageAdapter.setItems(models);
		scrollBottom();
	}

	// 添加聊天多条数据
	@Override
	public void addMessages(List<LiveBarrageModel> models) {
		if (models == null) return;

		if (isTouch) {
			addStorageList(models);
			if (onScrollMessageListener != null) {
				// 计算新消息数量
				onScrollMessageListener.calculateMessageCount(models.size());
			}
			return;
		}
		// 如果最后一条是进入直播间提示
		if (isIntoRoomTip()) {
			if (barrageAdapter.getItemCount() > 0) {
				// 删除最后一条
				barrageAdapter.delete(barrageAdapter.getItemCount() - 1);
			}
			// 如果进入直播间的数据不为空，再讲其添加进聊天列表
			if (this.intoRoomTipModel != null) models.add(intoRoomTipModel);
		}

		// 讲新增数据添加到列表
		barrageAdapter.addList(models);

		handleAddData(models.size(), false);
	}

	// 添加聊天单条数据
	@Override
	public void addMessage(LiveBarrageModel model) {
		if (model == null) return;

		resetModels();

		models.add(model);

		addMessages(models);
	}

	// 添加提醒单条数据
	@Override
	public void addTipMessage(LiveBarrageModel model) {
		if (model == null) return;

		// 记录进入直播数据
		this.intoRoomTipModel = model;

		if (isTouch || !isScrollBottom()) {
			return;
		}

		// 如果最后一条是进入直播间提示
		if (isIntoRoomTip()) {
			// 更新聊天列表最后一条数据
			barrageAdapter.getItems().set(barrageAdapter.getItemCount() - 1, model);
			barrageAdapter.notifyItemChanged(barrageAdapter.getItemCount() - 1);
		} else {
			// 否则增加最后一条数据
			barrageAdapter.addItem(barrageAdapter.getItemCount(), model);
		}

		handleAddData(1, true);
	}

	// 处理添加数据
	private synchronized void handleAddData(int size, boolean isIntoRoom) {
		// 如果没有触摸聊天列表并且滑动到了底部
		if (!isTouch && isScrollBottom()) {
			// 未处于暂停状态
			if (!isPause) {
				// 检查是否超过固定数量
				checkRemoveList(barrageAdapter.getItemCount() - MAX_COUNT);
				scrollBottom();
			}
		} else {
			if (!isIntoRoom) if (onScrollMessageListener != null) {
				// 计算新消息数量
				onScrollMessageListener.calculateMessageCount(size);
				// 如果没有触摸聊天列表 展示新消息框
				if (!isTouch) onScrollMessageListener.showMessageCount(true);
			}
		}
	}

	// 添加滑动缓存 当滑动的时候发来新的聊天消息 记录缓存起来
	private void addStorageList(List<LiveBarrageModel> models) {
		getStorageList().clear();
		getStorageList().addAll(models);
	}

	// 获取缓存的集合
	public List<LiveBarrageModel> getStorageList() {
		if (storageList == null) storageList = new ArrayList<>();
		return storageList;
	}

	// 重置记录数据
	private void resetModels() {
		if (models != null) models.clear();
		else models = new ArrayList<>();
	}

	// 设置暂停状态 为了防止切换后台界面还在刷新
	public void setPause(boolean pause) {
		this.isPause = pause;
		if (!isPause) scrollNearBottom();
	}

	// 移除大于最大条时最开始的数据
	public void checkRemoveList(int size) {
		if (barrageAdapter.getItemCount() > MAX_COUNT) {
			for (int i = 0; i < size; i++) {
				if (barrageAdapter.getItems() != null) {
					barrageAdapter.delete(i);
					i--;
					size--;
				}
			}
		}
	}

	// 滑动到底部
	public void scrollBottom() {
//		LiveHelper.mainLooper().execute(() -> smoothScrollToPosition(barrageAdapter.getItemCount() - 1));
		smoothScrollToPosition(barrageAdapter.getItemCount() - 1);
	}

	// 滚动接近底部 然后动效滚动到底部
	public void scrollNearBottom() {
		// 如果当前处于距底部很多条数据的时候会滑动好长时间，所以先滑动到距底部倒数个数位置，然后在平滑滚动到底部
		if (barrageAdapter.getItemCount() - 1 - NEAR_BOTTOM_COUNT >= NEAR_BOTTOM_COUNT) scrollToPosition(barrageAdapter.getItemCount() - 1 - NEAR_BOTTOM_COUNT);
		scrollBottom();
	}

	@Override public void onScrolled(int dx, int dy) {
		super.onScrolled(dx, dy);
		LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
		if (layoutManager != null) {
			int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
			isScrollBottom = lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1;
		}
		// 滚动到底部隐藏新消息弹框
		if (isScrollBottom() && onScrollMessageListener != null && !isTouch) onScrollMessageListener.showMessageCount(false);
	}

	// 是否聊天列表滚动到底部
	public boolean isScrollBottom() {
		return isScrollBottom;
	}

	// 是否最后一条是新进入的提示
	private boolean isIntoRoomTip() {
		return (barrageAdapter != null && barrageAdapter.getItemCount() > 0 && barrageAdapter.getItem(barrageAdapter.getItemCount() - 1) != null)
				&& barrageAdapter.getItem(barrageAdapter.getItemCount() - 1).isIntoRoomTip;
	}

	// 是否最后一条是新进入的提示
	private boolean isHideIntoRoomTip() {
		return (barrageAdapter != null && barrageAdapter.getItemCount() > 0 && barrageAdapter.getItem(barrageAdapter.getItemCount() - 1) != null)
				&& barrageAdapter.getItem(barrageAdapter.getItemCount() - 1).isHide;
	}

	// 更改最后一条状态
	public void updateLastItemStatus(boolean isShow) {
		if (isHideIntoRoomTip()) {
			LiveBarrageModel model = barrageAdapter.getItem(barrageAdapter.getItemCount() - 1);
			if (model != null) {
				model.isHide = isShow;
				// 更新聊天列表最后一条数据
				barrageAdapter.getItems().set(barrageAdapter.getItemCount() - 1, model);
				barrageAdapter.notifyItemChanged(barrageAdapter.getItemCount() - 1);
			}
		}
	}

	// 是否触摸聊天列表
	public boolean isTouch() {
		return isTouch;
	}

	@Override public boolean dispatchTouchEvent(MotionEvent ev) {
		int downX = (int) ev.getX();
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				isTouch = true;
				performClick();
				// 触摸时更新最后一条提醒进入直播间状态让它显示出来，并且悬浮的进入直播间布局隐藏
				if (onScrollMessageListener != null) onScrollMessageListener.isTouchMessage(isTouch);
				break;
			case MotionEvent.ACTION_MOVE:
				isTouch = !(Math.abs(ev.getX() - downX) > 0);
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				isTouch = false;
				performClick();
				// 触摸时更新最后一条提醒进入直播间状态让它隐藏，并且悬浮的进入直播间布局显示
				if (onScrollMessageListener != null) onScrollMessageListener.isTouchMessage(isTouch);
				if (isScrollBottom()) {// 抬起时处于底部时添加缓存的消息数据
					addStorageData();
				}
				break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// 添加缓存的数据
	public void addStorageData() {
		addMessages(getStorageList());
		// 清空缓存否则再次添加会添加许多条
		if (storageList != null) storageList.clear();
	}

	@Override protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		detach();
	}

	public void detach() {
		if (storageList != null) storageList.clear();
		storageList = null;
		if (models != null) models.clear();
		models = null;
		onScrollMessageListener = null;
		barrageAdapter = null;
		intoRoomTipModel = null;
	}

	public void setOnScrollMessageListener(OnScrollMessageListener onScrollMessageListener) {
		this.onScrollMessageListener = onScrollMessageListener;
	}

	public interface OnScrollMessageListener {

		void showMessageCount(boolean isShow);

		void calculateMessageCount(int count);

		void isTouchMessage(boolean isTouch);
	}
}
