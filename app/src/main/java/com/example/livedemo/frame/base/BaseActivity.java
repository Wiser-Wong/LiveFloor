package com.example.livedemo.frame.base;

import android.os.Bundle;

import com.wiser.library.base.IWISERBiz;
import com.wiser.library.base.WISERActivity;

import butterknife.ButterKnife;

/**
 * @author wangxy
 * @param <T>
 *            业务类
 * 
 *            基类
 */
public abstract class BaseActivity<T extends IWISERBiz> extends WISERActivity<T> {

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this);
	}
}
