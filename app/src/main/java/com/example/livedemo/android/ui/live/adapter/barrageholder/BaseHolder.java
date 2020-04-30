package com.example.livedemo.android.ui.live.adapter.barrageholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.wiser.library.adapter.WISERHolder;

import butterknife.ButterKnife;

public class BaseHolder<T> extends WISERHolder<T> {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindData(T t, int position) {

    }
}
