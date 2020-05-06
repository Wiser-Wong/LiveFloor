package com.example.livedemo.android.util;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorBottomView;
import com.example.livedemo.android.ui.live.fragment.ILiveVideoFloorMessageView;
import com.example.livedemo.android.ui.live.fragment.LiveFloorBottomFragment;
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
    public static void shutUp(AppCompatActivity activity, boolean isShutUp) {
        if (activity == null) return;
        ILiveVideoFloorBottomView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.setShutUp(isShutUp);
    }

    //踢出直播间
    public static void kickOut(boolean isKickOut) {
    }

    //初始化消息
    public static void initMessages(AppCompatActivity activity, List<LiveBarrageModel> models) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.initMessages(models);
    }

    //初始化消息
    public static void initMessage(AppCompatActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.initMessage(model);
    }

    //添加多条消息
    public static void addMessages(AppCompatActivity activity, List<LiveBarrageModel> models) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addMessages(models);
    }

    //添加单条消息
    public static void addMessage(AppCompatActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addMessage(model);
    }

    //添加提醒单条消息
    public static void addTipMessage(AppCompatActivity activity, LiveBarrageModel model) {
        if (activity == null) return;
        ILiveVideoFloorMessageView fragment = (LiveFloorBottomFragment) LiveHelper.display().findFragment(LiveFloorBottomFragment.class.getName());
        if (fragment == null) return;
        fragment.addTipMessage(model);
    }

}
