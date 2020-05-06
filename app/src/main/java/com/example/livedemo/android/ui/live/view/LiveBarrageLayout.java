package com.example.livedemo.android.ui.live.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorMessageView;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author wangxy
 * 
 *         弹幕布局
 */
public class LiveBarrageLayout extends FrameLayout implements LiveBarrageRecycleView.OnScrollMessageListener, View.OnClickListener, ILiveVideoFloorMessageView {

	private LiveBarrageRecycleView			rlvBarrage;					// 弹幕控件

	private TextView						tvBarrageNewMsgCount;		// 弹幕新消息数

	private LiveBarrageIntoRoomTipLayout	tipLayout;					// 提示进入房间布局

	private boolean							isRunningAnim;				// 是否正在运行动画

	private static final boolean			IS_ANIM			= false;	// 是否展示消息使用动画

	private static final int				MAX_MSG_COUNT	= 99;		// 最大消息之后展示99+

	private long							count;						// 记录消息数

	private boolean							isPause;					// 是否暂停

	public LiveBarrageLayout(@NonNull Context context) {
		super(context);
		init();
	}

	public LiveBarrageLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.live_floor_bottom_barrage_layout, this, true);
		rlvBarrage = findViewById(R.id.rlv_barrage);
		tvBarrageNewMsgCount = findViewById(R.id.tv_barrage_new_message_count);
		tipLayout = findViewById(R.id.layout_into_room_tip);

		rlvBarrage.setOnScrollMessageListener(this);
		tvBarrageNewMsgCount.setOnClickListener(this);
	}

	// 添加垂直弹幕数据
	@Override
	public void initMessages(List<LiveBarrageModel> models) {
		if (models == null) return;
		rlvBarrage.initMessages(models);
	}

	// 添加垂直弹幕数据
	@Override
	public void initMessage(LiveBarrageModel model) {
		if (model == null) return;
		rlvBarrage.initMessage(model);
	}

	// 添加聊天多条数据
	@Override
	public synchronized void addMessages(List<LiveBarrageModel> models) {
		if (models == null) return;
		rlvBarrage.addMessages(models);
	}

	// 添加聊天单条数据
	@Override
	public synchronized void addMessage(LiveBarrageModel model) {
		if (model == null) return;
		rlvBarrage.addMessage(model);
	}

	// 添加提醒单条数据
	@Override
	public synchronized void addTipMessage(LiveBarrageModel model) {
		if (model == null) return;
		rlvBarrage.addTipMessage(model);

		if (!rlvBarrage.isTouch() && rlvBarrage.isScrollBottom()) {
			if (!isPause) {
				tipLayout.intoRoomTipData(model);
			}
		}
	}

	// 展示新消息数量
	public void showNewMsgCountAnim(boolean isShow) {
		if (IS_ANIM) {
			if (isRunningAnim) return;
			if (isShow && tvBarrageNewMsgCount.getVisibility() == View.VISIBLE) return;
			if (!isShow && tvBarrageNewMsgCount.getVisibility() == View.GONE) return;
			ObjectAnimator animator;
			if (isShow) {
				animator = ObjectAnimator.ofFloat(tvBarrageNewMsgCount, "translationY", tvBarrageNewMsgCount.getMeasuredHeight(), 0);
			} else {
				animator = ObjectAnimator.ofFloat(tvBarrageNewMsgCount, "translationY", tvBarrageNewMsgCount.getMeasuredHeight());
			}
			animator.setDuration(200);
			animator.setInterpolator(new DecelerateInterpolator());
			animator.addListener(new AnimatorListenerAdapter() {

				@Override public void onAnimationStart(Animator animation) {
					super.onAnimationStart(animation);
					isRunningAnim = true;
					tvBarrageNewMsgCount.setVisibility(View.VISIBLE);
				}

				@Override public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					isRunningAnim = false;
					if (isShow) tvBarrageNewMsgCount.setVisibility(View.VISIBLE);
					else {
						tvBarrageNewMsgCount.setVisibility(View.GONE);
						resetMessageCount();
					}
				}

				@Override public void onAnimationCancel(Animator animation) {
					super.onAnimationCancel(animation);
					isRunningAnim = false;
					if (isShow) tvBarrageNewMsgCount.setVisibility(View.VISIBLE);
					else {
						tvBarrageNewMsgCount.setVisibility(View.GONE);
						resetMessageCount();
					}
				}
			});

			animator.start();
		} else {
			if (isShow) {
				if (tvBarrageNewMsgCount.getVisibility() == GONE) tvBarrageNewMsgCount.setVisibility(View.VISIBLE);
			} else {
				if (tvBarrageNewMsgCount.getVisibility() == VISIBLE) {
					tvBarrageNewMsgCount.setVisibility(View.GONE);
				}
				resetMessageCount();
			}
		}
	}

	// 设置消息数量文本
	private void setMessageCountText() {
		if (this.count > MAX_MSG_COUNT) tvBarrageNewMsgCount.setText(MessageFormat.format("{0}+条新消息", MAX_MSG_COUNT));
		else tvBarrageNewMsgCount.setText(MessageFormat.format("{0}条新消息", this.count));
	}

	// 重置消息数量
	private void resetMessageCount() {
		LiveBarrageLayout.this.count = 0;
	}

	// 设置暂停
	public void setPause(boolean isPause) {
		this.isPause = isPause;
		if (rlvBarrage != null) rlvBarrage.setPause(isPause);
	}

	public void detach() {
		if (rlvBarrage != null) rlvBarrage.detach();
		rlvBarrage = null;
		tipLayout = null;
		tvBarrageNewMsgCount = null;
	}

	@Override public void showMessageCount(boolean isShow) {
		if (isPause) return;
		showNewMsgCountAnim(isShow);
		if (isShow && this.count > 0) setMessageCountText();
	}

	@Override public void calculateMessageCount(int count) {
		this.count = this.count + count;
	}

	@Override public void isTouchMessage(boolean isTouch) {
		if (isTouch) {
			tipLayout.setHideUi(true);
			rlvBarrage.updateLastItemStatus(false);
		} else {
			if (rlvBarrage.isScrollBottom()) {
				tipLayout.setHideUi(false);
				rlvBarrage.updateLastItemStatus(true);
			}
		}
	}

	@Override public void onClick(View v) {
		if (v.getId() == R.id.tv_barrage_new_message_count) {// 新消息布局
			//处理显示进入主播间消息布局
			tipLayout.setHideUi(false);
			//更新聊天列表最后一条消息状态让最后一条消息隐藏
			rlvBarrage.updateLastItemStatus(true);
			//隐藏新消息数量
			showNewMsgCountAnim(false);
			rlvBarrage.addStorageData();
			//滚动到底部
			rlvBarrage.scrollNearBottom();
		}
	}
}
