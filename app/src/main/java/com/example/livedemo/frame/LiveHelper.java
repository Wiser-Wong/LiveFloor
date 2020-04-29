package com.example.livedemo.frame;

import com.example.livedemo.android.config.LiveConfig;
import com.wiser.library.helper.WISERHelper;

/**
 * @author wangxy
 *
 *         帮助类
 */
public class LiveHelper extends WISERHelper {

	/**
	 * 存储配置
	 * 
	 * @return
	 */
	public static LiveConfig config() {
		LiveManage manage = getManage();
		return manage.config();
	}

}
