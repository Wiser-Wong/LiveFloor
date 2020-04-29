package com.example.livedemo.frame;

import android.app.Application;

public class LiveApplication extends Application {

	@Override public void onCreate() {
		super.onCreate();
		LiveHelper.newBind().setWiserBind(new LiveBind()).inject(this, true);
	}
}
