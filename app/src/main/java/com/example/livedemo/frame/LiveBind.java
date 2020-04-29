package com.example.livedemo.frame;

import android.app.Application;

import com.wiser.library.base.IWISERBind;
import com.wiser.library.manager.WISERManage;

public class LiveBind implements IWISERBind {

	@Override public void initApplication(Application application) {

	}

	@Override public WISERManage getManage() {
		return new LiveManage();
	}
}
