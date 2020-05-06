package com.example.livedemo.android.ui.live.fragment;

import java.util.List;

import com.example.livedemo.android.ui.live.model.LiveBarrageModel;

/**
 * @author wangxy
 *
 * floor层消息处理交互接口
 */
public interface ILiveVideoFloorMessageView {

    void initMessages(List<LiveBarrageModel> barrageModels);

    void initMessage(LiveBarrageModel barrageModel);

    void addMessages(List<LiveBarrageModel> barrageModels);

    void addMessage(LiveBarrageModel barrageModel);

    void addTipMessage(LiveBarrageModel barrageModel);
}