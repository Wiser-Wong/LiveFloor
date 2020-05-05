package com.example.livedemo.android.ui.live.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author wangxy
 * 
 *         表情分页适配器
 */
public class FacePageAdapter extends PagerAdapter {

	private List<View> pageViews;

	public FacePageAdapter(List<View> pageViews) {
		super();
		this.pageViews = pageViews;
	}

	// 显示数目
	@Override public int getCount() {
		return pageViews.size();
	}

	@Override public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
		return arg0 == arg1;
	}

	@Override public int getItemPosition(@NonNull Object object) {
		return super.getItemPosition(object);
	}

	@Override public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
		container.removeView(pageViews.get(position));
	}

	@NonNull @Override public Object instantiateItem(ViewGroup container, int position) {
		container.addView(pageViews.get(position));
		return pageViews.get(position);
	}
}