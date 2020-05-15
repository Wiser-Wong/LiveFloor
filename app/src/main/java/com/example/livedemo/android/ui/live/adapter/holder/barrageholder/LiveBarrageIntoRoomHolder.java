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
import com.wiser.library.util.WISERApp;
import com.wiser.library.util.WISERCheck;

/**
 * @author wangxy
 *
 * 弹幕holder
 */
public class LiveBarrageIntoRoomHolder extends BaseHolder<LiveBarrageModel> {

    private TextView tvLiveChatContent;

    public LiveBarrageIntoRoomHolder(@NonNull View itemView) {
        super(itemView);
        tvLiveChatContent = itemView.findViewById(R.id.tv_live_chat_content);
    }

    @Override
    public void bindData(LiveBarrageModel model, int position) {
        if (model == null) return;
        if (position == adapter().getItemCount() - 1 && model.isHideLastItem) {
            itemView.setVisibility(View.INVISIBLE);
            tvLiveChatContent.setVisibility(View.INVISIBLE);
        } else {
            itemView.setVisibility(View.VISIBLE);
            tvLiveChatContent.setVisibility(View.VISIBLE);
        }
        String nickName = WISERCheck.isEmpty(model.nickName) ? "" : model.nickName;
        SpannableString spannableString = null;
        if (model.type == LiveBarrageType.CHAT_INTO_ROOM) {//进入直播间
            if (model.isVip) {
                spannableString = new SpannableString("icon " + nickName + "来了");
            } else {
                spannableString = new SpannableString(nickName + "来了");
            }
        }
        if (spannableString != null) {
            if (model.isVip){
                Drawable drawable = getContext().getResources().getDrawable(R.drawable.live_video_icon_vip);
                drawable.setBounds(0, 0, WISERApp.dip2px(13), WISERApp.dip2px(13));
                spannableString.setSpan(new CenterImageSpan(drawable), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 5, nickName.length() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else {
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FEE274")), 0, nickName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tvLiveChatContent.setText(spannableString);
        }
    }
}