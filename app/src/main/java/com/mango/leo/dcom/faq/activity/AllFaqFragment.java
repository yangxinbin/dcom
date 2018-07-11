package com.mango.leo.dcom.faq.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.dcom.R;

/**
 * Created by admin on 2018/7/10.
 */

@SuppressLint("ValidFragment")
class AllFaqFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_view, container, false);
        return view;
    }
}
