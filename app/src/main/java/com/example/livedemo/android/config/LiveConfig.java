package com.example.livedemo.android.config;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wiser.library.annotation.property.Property;
import com.wiser.library.config.property.WISERProperties;

public class LiveConfig extends WISERProperties {

	public LiveConfig(@NonNull Context context) {
		super(context, "liveConfig");
	}

	@Override public int initType() {
		return WISERProperties.OPEN_TYPE_DATA;
	}

	// 键盘高度
	@Property("keyboardHeight") public int keyboardHeight;

}
