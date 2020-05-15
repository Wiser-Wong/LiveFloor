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

    public boolean isMine;

    public int type;//消息类型

    public boolean isIntoRoomTip;//是否是用户进入直播间提醒

    public boolean isHideLastItem;//是否隐藏用户进入直播间最后一条显示的消息

    public LiveBarrageModel(){

    }

    public LiveBarrageModel(boolean isVip, boolean isMine,String nickName, String content, int type, boolean isIntoRoomTip, boolean isHideLastItem) {
        this.isVip = isVip;
        this.isMine = isMine;
        this.nickName = nickName;
        this.content = content;
        this.type = type;
        this.isIntoRoomTip = isIntoRoomTip;
        this.isHideLastItem = isHideLastItem;
    }
}