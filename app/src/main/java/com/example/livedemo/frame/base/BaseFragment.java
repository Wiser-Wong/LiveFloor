package com.example.livedemo.frame.base;

import android.os.Bundle;

import com.wiser.library.base.IWISERBiz;
import com.wiser.library.base.WISERFragment;

import java.util.Objects;

import butterknife.ButterKnife;

/**
 * @author wangxy
 * @param <T>
 *            业务类
 *
 *            基类
 */
public abstract class BaseFragment<T extends IWISERBiz> extends WISERFragment<T> {

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(Objects.requireNonNull(getActivity()));
	}
}
