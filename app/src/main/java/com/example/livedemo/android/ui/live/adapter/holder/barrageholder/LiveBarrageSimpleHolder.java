package com.example.livedemo.android.ui.live.adapter.holder.barrageholder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.livedemo.R;
import com.example.livedemo.android.ui.live.adapter.holder.BaseHolder;
import com.example.livedemo.android.ui.live.model.LiveBarrageModel;
import com.example.livedemo.android.util.LiveBarrageType;
import com.example.livedemo.android.view.CenterImageSpan;
import com.example.livedemo.frame.LiveHelper;
import com.wiser.library.util.WISERApp;
import com.wiser.library.util.WISERCheck;

/**
 * @author wangxy
 *
 * 弹幕holder
 */
public class LiveBarrageSimpleHolder extends BaseHolder<LiveBarrageModel> {

    private TextView tvLiveChatContent;

    public LiveBarrageSimpleHolder(@NonNull View itemView) {
        super(itemView);
        tvLiveChatContent = itemView.findViewById(R.id.tv_live_chat_content);
    }

    @Override
    public void bindData(LiveBarrageModel model, int position) {
        if (model == null) return;
        String nickName = WISERCheck.isEmpty(model.nickName) ? "" : model.nickName;
        String content = WISERCheck.isEmpty(model.content) ? "" : model.content;
        SpannableString spannableString = null;
        if (model.type == LiveBarrageType.CHAT_SHARE) {//分享直播间
            if (model.isVip) {
                spannableString = new SpannableString("icon " + nickName + "分享了直播间");
            } else {
                spannableString = new SpannableString(nickName + "分享了直播间");
            }
            if (model.isVip) {
                Drawable drawable = getContext().getResources().getDrawable(R.drawable.live_video_icon_vip);
                drawable.setBounds(0, 0, WISERApp.dip2px( 13), WISERApp.dip2px(13));
                spannableString.setSpan(new CenterImageSpan(drawable), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 5, nickName.length() + 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else {
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 0, nickName.length() + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tvLiveChatContent.setText(spannableString);
        } else {//普通评论
            if (model.isVip) {
                spannableString = new SpannableString("icon " + nickName + ":" + content);
                Drawable drawable = getContext().getResources().getDrawable(R.drawable.live_video_icon_vip);
                drawable.setBounds(0, 0, WISERApp.dip2px(13), WISERApp.dip2px(13));
                spannableString.setSpan(new CenterImageSpan(drawable), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 0, nickName.length() + 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvLiveChatContent.setText(spannableString);
            } else {
                if (model.isMine) {
                    spannableString = new SpannableString(nickName + ":" + content);
                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#A3FFE5")), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvLiveChatContent.setText(spannableString);
                } else {
                    spannableString = new SpannableString(nickName + ":" + content);
                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 0, nickName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvLiveChatContent.setText(spannableString);
                }
            }
        }
    }
}