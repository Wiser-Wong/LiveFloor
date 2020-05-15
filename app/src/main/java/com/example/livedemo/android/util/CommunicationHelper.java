package com.example.livedemo.android.util;

import androidx.fragment.app.FragmentActivity;

import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorBottomView;
import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorMessageView;
import com.example.livedemo.android.ui.live.fragment.LiveFloorBottomFragment;
import com.example.livedemo.android.ui.live.fragment.LiveFloorFragment;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.frame.LiveHelper;

import java.util.List;


/**
 * @author wangxy
 *
 * 通讯帮助类
 */
public class CommunicationHelper {

    //禁言
    public static void shutUp(FragmentActivity activity, boolean isShutUp) {
        if (activity == null) return;
        ILiveVideoFloorBottomView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.setShutUp(isShutUp);
    }

    //踢出直播间
    public static void kickOut(boolean isKickOut) {
    }

    // 直播结束
    public static void setLiveEnd(FragmentActivity activity){
        if (activity == null) return;
        LiveFloorFragment fragment = LiveHelper.display().findFragment(LiveFloorFragment.class.getName());
        if (fragment == null) return;
        fragment.setLiveEnd(true);
    }

    //初始化消息
    public static void initMessages(FragmentActivity activity, List<LiveBarrageModel> models) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.initMessages(models);
    }

    //初始化消息
    public static void initMessage(FragmentActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.initMessage(model);
    }

    //添加多条消息
    public static void addMessages(FragmentActivity activity, List<LiveBarrageModel> models) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addMessages(models);
    }

    //添加单条消息
    public static void addMessage(FragmentActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addMessage(model);
    }

    //添加提醒单条消息
    public static void addTipMessage(FragmentActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addTipMessage(model);
    }

}
