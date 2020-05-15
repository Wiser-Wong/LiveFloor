package com.example.livedemo.android.ui.live.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.livedemo.R;

/**
 * **************************************
 * 项目名称:LiveDemo
 *
 * @author wangxy
 * 邮箱：wangxianyu@ksjgs.com
 * 创建时间: 2020/5/7 11:52 AM
 * 用途：
 * **************************************
 */
public class LiveVideoEndFragment extends Fragment implements View.OnClickListener {

    private AppCompatImageView ivLiveHead;

    private TextView tvLiveName;

    private TextView tvLiveTime;

    private TextView tvLiveAudience;

    private TextView tvLiveDiscuss;

    public static LiveVideoEndFragment createVideoEndFragment(){
        LiveVideoEndFragment fragment = new LiveVideoEndFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_video_end_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        ivLiveHead = view.findViewById(R.id.iv_live_end_head);
        tvLiveName = view.findViewById(R.id.tv_live_end_name);
        tvLiveTime = view.findViewById(R.id.tv_live_end_time);
        tvLiveAudience = view.findViewById(R.id.tv_live_end_audience_count);
        tvLiveDiscuss = view.findViewById(R.id.tv_live_end_discuss_count);

        view.findViewById(R.id.iv_live_end_close).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_live_end_close){//关闭
            if (getActivity() != null) getActivity().finish();
        }
    }
}
