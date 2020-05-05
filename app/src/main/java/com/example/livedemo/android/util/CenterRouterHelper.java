package com.example.livedemo.android.util;

import android.util.SparseArray;

import com.example.livedemo.android.ui.live.model.LiveBarrageModel;

/**
 * @author wangxy
 * 
 *         中间路由
 */
public class CenterRouterHelper {

	// 是否禁言
	private boolean							isShutUp;

	// 是否被踢出直播间
	private boolean							isKickOut;

	// 新消息数据
	private SparseArray<LiveBarrageModel>	barrageModels;

	private static final class CenterRouterHolder {

		private static final CenterRouterHelper instance = new CenterRouterHelper();
	}

	public static CenterRouterHelper getInstance() {
		return CenterRouterHolder.instance;
	}

	public boolean isShutUp() {
		return isShutUp;
	}

	public void setShutUp(boolean shutUp) {
		isShutUp = shutUp;
	}

	public boolean isKickOut() {
		return isKickOut;
	}

	public void setKickOut(boolean kickOut) {
		isKickOut = kickOut;
	}

	public void setBarrageModels(SparseArray<LiveBarrageModel> barrageModels) {
		this.barrageModels = barrageModels;
	}

	public SparseArray<LiveBarrageModel> getBarrageModels() {
		return barrageModels;
	}

	public void detach() {
		isShutUp = false;
		isKickOut = false;
		if (barrageModels != null) barrageModels.clear();
		barrageModels = null;
	}
}
