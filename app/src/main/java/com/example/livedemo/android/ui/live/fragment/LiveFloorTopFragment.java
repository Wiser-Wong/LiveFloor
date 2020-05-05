package com.example.livedemo.android.ui.live.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.livedemo.R;
import com.example.livedemo.frame.base.BaseFragment;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.util.WISERApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangxy
 *
 *         LiveFloorTopFragment 直播浮层顶部区域
 */
public class LiveFloorTopFragment extends BaseFragment<LiveFloorTopBiz> {

	@BindView(R.id.iv_top_photo) AppCompatImageView	ivTopPhoto;

	@BindView(R.id.cl_top_head) ConstraintLayout	clTopHead;

	private boolean									isRunningAnim;	// 是否运行贴纸动画

	@Override protected WISERBuilder build(WISERBuilder builder) {
		builder.layoutId(R.layout.live_floor_top_fragment);
		return builder;
	}

	@Override public void initCreateViewAfter(Bundle savedInstanceState) {
		super.initCreateViewAfter(savedInstanceState);
		ButterKnife.bind(this, getFragmentView());
	}

	@Override protected void initData(Bundle savedInstanceState) {
		if (builder().getContentView() != null) {
			builder().getContentView().findViewById(R.id.iv_top_head).setOnClickListener(new View.OnClickListener() {

				@Override public void onClick(View v) {
					showTopPhotoAnim(true);
				}
			});
			builder().getContentView().findViewById(R.id.tv_top_nickname).setOnClickListener(new View.OnClickListener() {

				@Override public void onClick(View v) {
					showTopPhotoAnim(false);
				}
			});
		}
	}

	// 显示顶部用户区域 会根据键盘弹起消失变化显示隐藏
	public void showTopHead(boolean isHide) {
		if (isHide) {
			if (clTopHead.getVisibility() == View.VISIBLE) clTopHead.setVisibility(View.GONE);
		} else {
			if (clTopHead.getVisibility() == View.GONE) clTopHead.setVisibility(View.VISIBLE);
		}
	}

	// 展示顶部贴纸
	public void showTopPhotoAnim(boolean isShow) {
		if (isRunningAnim) return;
		if (isShow && ivTopPhoto.getVisibility() == View.VISIBLE) return;
		if (!isShow && ivTopPhoto.getVisibility() == View.GONE) return;
		ObjectAnimator animator;
		if (isShow) {
			animator = ObjectAnimator.ofFloat(ivTopPhoto, "translationY", -WISERApp.dip2px(240), 0);
		} else {
			animator = ObjectAnimator.ofFloat(ivTopPhoto, "translationY", -WISERApp.dip2px(240));
		}
		animator.setDuration(400);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.addListener(new AnimatorListenerAdapter() {

			@Override public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				isRunningAnim = true;
				ivTopPhoto.setVisibility(View.VISIBLE);
			}

			@Override public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				isRunningAnim = false;
				if (isShow) ivTopPhoto.setVisibility(View.VISIBLE);
				else ivTopPhoto.setVisibility(View.GONE);
			}

			@Override public void onAnimationCancel(Animator animation) {
				super.onAnimationCancel(animation);
				isRunningAnim = false;
				if (isShow) ivTopPhoto.setVisibility(View.VISIBLE);
				else ivTopPhoto.setVisibility(View.GONE);
			}
		});

		animator.start();
	}
}
