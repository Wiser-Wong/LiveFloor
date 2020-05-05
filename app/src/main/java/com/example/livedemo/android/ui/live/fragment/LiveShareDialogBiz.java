package com.example.livedemo.android.ui.live.fragment;

/**
 * @author wangxy
 * 
 *         分享业务
 */
public class LiveShareDialogBiz {

    private LiveShareDialogFragment ui;

    public LiveShareDialogBiz(LiveShareDialogFragment ui){
        this.ui = ui;
    }

    //分享微信
    public void shareWeChat(){

    }

    //分享朋友圈
    public void shareFriendCircle(){

    }

    public void detach(){
        ui = null;
    }

}
