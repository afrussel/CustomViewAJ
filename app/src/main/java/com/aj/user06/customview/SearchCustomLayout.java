package com.aj.user06.customview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Anjan Debnath on 6/25/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class SearchCustomLayout extends LinearLayout {

    private TextView textSearchTxt;
    private EditText editTextSearchVideoEdit;
    private EditText editSearchPeople;
    private CheckBox cbAvatar;

    public SearchCustomLayout(Context context) {
        super(context);
    }

    public SearchCustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchCustomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void init(Context context){

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        ViewGroup viewgroup = (ViewGroup) layoutInflater.inflate(
                R.layout.search_handler, this);


    }
}
