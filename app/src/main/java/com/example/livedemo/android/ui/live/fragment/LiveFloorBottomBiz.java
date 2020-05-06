package com.example.livedemo.android.ui.live.fragment;

import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;
import com.wiser.library.base.WISERBiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangxy
 * 
 *         直播浮层底部状态栏区域业务
 */
public class LiveFloorBottomBiz extends WISERBiz<LiveFloorBottomFragment> {

	private String[] nickNames = new String[] { "天才", "傻瓜", "林俊杰","杨丞琳" };
	private String[] content = new String[] { "那女孩对我说，说我是个傻瓜", "我命由我不由天", "我太难了啊", "直播间内严禁出现违法违规、低俗、色情、吸烟酗酒等内容，若有违规行为请及时举报" };

	private String[]	data1	= new String[] { "小倩进入直播间", "鬼吹灯进入直播间", "玉皇大帝进入直播间", "孙悟空进入直播间" };

	public List<LiveBarrageModel> firstData() {
		List<LiveBarrageModel> models = new ArrayList<>();
		LiveBarrageModel model = new LiveBarrageModel();
		model.content = "欢迎来到直播间！XXX严禁未成年进行直播或打赏，请大家共同准守、监督。直播间内严禁出现违法违规、低俗、色情、吸烟酗酒等内容，若有违规行为请及时举报。如主播在直播过程中以陪玩、送礼等方式进行诱导打赏、私下交易，清谨慎判断，以防人身或财产损失。";
		model.type = LiveBarrageType.CHAT_TIP;
		models.add(model);
		return models;
	}

	public List<LiveBarrageModel> addData() {
		List<LiveBarrageModel> models = new ArrayList<>();
		int random = new Random().nextInt(4);
		for (int i = 0; i < random; i++) {
			LiveBarrageModel model = new LiveBarrageModel();
			model.nickName = nickNames[random];
			model.content = content[random];
			model.type = LiveBarrageType.CHAT_VIP;
			models.add(model);
		}
		return models;
	}

	public LiveBarrageModel addItem() {
		LiveBarrageModel model = new LiveBarrageModel();
		model.nickName = data1[new Random().nextInt(4)];
		model.type = LiveBarrageType.CHAT_VIP;
		model.isIntoRoomTip = true;
		model.isHide = true;
		return model;
	}

}
