package com.example.livedemo.android.ui.live.fragment;

import com.example.livedemo.android.ui.live.model.LiveShoppingBagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxy
 * 
 *         购物袋业务
 */
public class LiveShoppingBagDialogBiz {

    private LiveShoppingBagDialogFragment ui;

    public LiveShoppingBagDialogBiz(LiveShoppingBagDialogFragment ui){
        this.ui = ui;
    }

    //获取购物袋商品数据
    public List<LiveShoppingBagModel> getShoppingBagData(){
        List<LiveShoppingBagModel> models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LiveShoppingBagModel model = new LiveShoppingBagModel();
            models.add(model);
        }
        return models;
    }

    public void detach(){
        ui = null;
    }

}
