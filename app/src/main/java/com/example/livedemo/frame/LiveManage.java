package com.example.livedemo.frame;

import com.example.livedemo.android.config.LiveConfig;
import com.wiser.library.manager.WISERManage;

/**
 * @author wangxy
 *
 *         管理器
 */
public class LiveManage extends WISERManage {

	private LiveConfig config;

	/**
	 * 获取Config properties缓存管理
	 *
	 * @return
	 */
	public LiveConfig config() {
		if (config == null) synchronized (LiveConfig.class) {
			if (config == null) config = new LiveConfig(getApplication());
		}
		return config;
	}

}
