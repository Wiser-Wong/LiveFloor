package com.example.livedemo.android.ui.live.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.FaceAdapter;
import com.example.livedemo.android.ui.live.adapter.FacePageAdapter;
import com.example.livedemo.android.ui.live.model.LiveChatEmojiModel;
import com.example.livedemo.android.util.FaceConversionUtil;
import com.example.livedemo.frame.LiveHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxy
 * 
 *         表情布局
 */

public class LiveFaceViewPager extends ViewPager implements FaceAdapter.OnFaceItemClickListener {

	private ArrayList<View>				facePageViews	= new ArrayList<>();

	private List<List<LiveChatEmojiModel>>	emojis			= new ArrayList<>();

	private List<FaceAdapter>			faceAdapters	= new ArrayList<>();

	private int							current			= 0;

	private AppCompatEditText			etChatInput;

	private LinearLayout				llChatFacePoint;

	public LiveFaceViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void initFace(LinearLayout llChatFacePoint) {
		this.llChatFacePoint = llChatFacePoint;
		initFaceView();
		listenerPageChange();
	}

	// 初始化表情布局
	private void initFaceView() {
		emojis = FaceConversionUtil.getInstace().emojiLists;
		for (int i = 0; i < emojis.size(); i++) {
			RecyclerView view = new RecyclerView(getContext());
			view.setLayoutManager(new GridLayoutManager(getContext(), 7));
			FaceAdapter adapter = new FaceAdapter(getContext(), emojis.get(i));
			view.setAdapter(adapter);
			adapter.setOnFaceItemClickListener(this);
			faceAdapters.add(adapter);
			view.setBackgroundColor(Color.TRANSPARENT);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			facePageViews.add(view);
		}

		for (int i = 0; i < emojis.size(); i++) {
			AppCompatImageView pointView = new AppCompatImageView(getContext());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
			pointView.setPadding(5, 5, 5, 5);
			pointView.setLayoutParams(params);
			if (i == 0) {
				pointView.setImageResource(R.drawable.point_grey);
			} else {
				pointView.setImageResource(R.drawable.point_red);
			}
			if (llChatFacePoint != null) llChatFacePoint.addView(pointView);
		}
		setAdapter(new FacePageAdapter(facePageViews));
	}

	// 监听ViewPager页改变
	private void listenerPageChange() {
		addOnPageChangeListener(new OnPageChangeListener() {

			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

			@Override public void onPageSelected(int position) {
				current = position;
				if (llChatFacePoint == null) return;
				for (int i = 0; i < llChatFacePoint.getChildCount(); i++) {
					AppCompatImageView pointView = (AppCompatImageView) llChatFacePoint.getChildAt(i);
					if (pointView != null) {
						if (position == i) pointView.setImageResource(R.drawable.point_grey);
						else pointView.setImageResource(R.drawable.point_red);
					}
				}
			}

			@Override public void onPageScrollStateChanged(int state) {}
		});
	}

	// 添加输入框View
	public void setChatInputView(AppCompatEditText etChatInput) {
		this.etChatInput = etChatInput;
	}

	@Override public void onFaceItemClick(View view, int position) {
		if (etChatInput == null) return;
		LiveChatEmojiModel emoji = (LiveChatEmojiModel) faceAdapters.get(current).getItem(position);
		if (emoji.getId() == R.drawable.chat_face_del_sl) {
			int selection = etChatInput.getSelectionStart();
			String text = etChatInput.getText().toString();
			if (selection > 0) {
				String text2 = text.substring(selection - 1);
				if (":".equals(text2)) {
					String str = text.substring(0, selection - 1);
					int start = str.lastIndexOf(":");
					if (selection > start) etChatInput.getText().delete(start, selection);
					return;
				}
				etChatInput.getText().delete(selection - 1, selection);
			}

		}
		if (!TextUtils.isEmpty(emoji.getCharacter())) {
			SpannableString spannableString = FaceConversionUtil.getInstace().addFace(getContext(), emoji.getId(), emoji.getCharacter(), etChatInput);
			if (etChatInput.length() + spannableString.length() > LiveBottomLayout.MAX_INPUT_LIMIT) {
				LiveHelper.toast().show("限制" + LiveBottomLayout.MAX_INPUT_LIMIT + "个字符。");
				return;
			}
			etChatInput.append(spannableString);
		}
	}

	@Override protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		detach();
	}

	public void detach() {
		if (facePageViews != null) facePageViews.clear();
		facePageViews = null;
		if (emojis != null) emojis.clear();
		emojis = null;
		if (faceAdapters != null) faceAdapters.clear();
		faceAdapters = null;
		etChatInput = null;
		llChatFacePoint = null;
	}
}
