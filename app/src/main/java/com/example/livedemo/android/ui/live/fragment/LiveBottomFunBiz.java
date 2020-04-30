package com.example.livedemo.android.ui.live.fragment;

import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;
import com.wiser.library.base.WISERBiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LiveBottomFunBiz extends WISERBiz<LiveBottomFunFragment> {

    private String[] data = new String[]{"贱贱贱贱贱贱贱贱贱贱贱","叽叽叽叽急急急","简历时刻打飞机了深刻的房间里上课的方式来得快复健科","塑料袋副驾驶的路口附近的时空裂缝建档立卡司法局来看待时间分类的时间里"};

    public List<LiveBarrageModel> firstData(){
        List<LiveBarrageModel> models = new ArrayList<>();
        LiveBarrageModel model = new LiveBarrageModel();
        model.name = "欢迎来到直播间！XXX严禁未成年进行直播或打赏，请大家共同准守、监督。直播间内严禁出现违法违规、低俗、色情、吸烟酗酒等内容，若有违规行为请及时举报。如主播在直播过程中以陪玩、送礼等方式进行诱导打赏、私下交易，清谨慎判断，以防人身或财产损失。";
        model.type = LiveBarrageType.CHAT_TIP;
        models.add(model);
        return models;
    }

    public List<LiveBarrageModel> addData(){
        List<LiveBarrageModel> models = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            LiveBarrageModel model = new LiveBarrageModel();
            model.name = data[new Random().nextInt(4)];
            model.type = LiveBarrageType.CHAT_VIP;
            models.add(model);
        }
        return models;
    }

    public LiveBarrageModel addItem(){
        LiveBarrageModel model = new LiveBarrageModel();
        model.name = data[new Random().nextInt(3)];
        model.type = LiveBarrageType.CHAT_VIP;
        return model;
    }

}
