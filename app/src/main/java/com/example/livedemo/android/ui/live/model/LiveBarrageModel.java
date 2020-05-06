package com.example.livedemo.android.ui.live.model;

/**
 * @author wangxy
 * 
 *         弹幕数据
 */
public class LiveBarrageModel {

    public boolean isVip;

    public String nickName;

    public String content;

    public int type;//消息类型

    public boolean isIntoRoomTip;//是否是用户进入直播间提醒

    public boolean isHide;//是否隐藏用户进入直播间最后一条显示的消息
}