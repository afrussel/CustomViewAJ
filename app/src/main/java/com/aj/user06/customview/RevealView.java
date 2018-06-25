package com.aj.user06.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anjan Debnath on 6/25/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class RevealView extends FrameLayout {

    protected boolean toggle;
    protected Drawable lessArrow =    getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
    protected Drawable moreArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
    private TextView topBarText, bottomBarText;
    protected View topBar;
    protected ViewGroup bottomBar;
    protected ImageView toggleBottom;

    public RevealView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public RevealView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RevealView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setTitle(String title){
        topBarText.setText(title);
    }

    public void setMessage(String message){
        bottomBarText.setText(message);
    }

    public void setTitleBarColor(int color){
        topBar.setBackgroundColor(getResources().getColor(color));
    }

    public void setBottomBarColor(int color){
        bottomBar.setBackgroundColor(getResources().getColor(color));
    }

    protected OnClickListener setToggle(final ImageView toggleButton, final View bottomBar){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = lessArrow;
                int visibility = View.VISIBLE;
                if(toggle){
                    drawable = moreArrow;
                    visibility = View.GONE;
                    toggle = false;
                }else {
                    toggle = true;
                }
                toggleButton.setImageDrawable(drawable);
                bottomBar.setVisibility(visibility);
            }
        };

    }


    public void init(Context context, AttributeSet attributeSet){
        inflate(context, R.layout.search_handler, this);
        topBar = findViewById(R.id.topBar);
        bottomBar = (ViewGroup) findViewById(R.id.bottomBar);
        toggleBottom = (ImageView) findViewById(R.id.toggle_button);
        topBarText = (TextView) findViewById(R.id.top_bar_title);
        bottomBarText = (TextView) findViewById(R.id.bottom_bar_text);
        topBar.setOnClickListener(setToggle(toggleBottom, bottomBarText));
        if(attributeSet != null){
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RevealView);
            try {
                toggle = typedArray.getBoolean(R.styleable.RevealView_showMessage, false);
                bottomBar.setVisibility(toggle ? View.VISIBLE : View.GONE);
                toggleBottom.setImageDrawable(toggle ? lessArrow : moreArrow);
                if(typedArray.hasValue(R.styleable.RevealView_barTitle)){
                    topBarText.setText(typedArray.getString(R.styleable.RevealView_barTitle));
                }
                if(typedArray.hasValue(R.styleable.RevealView_colorBar)){
                    topBar.setBackgroundColor(typedArray.getColor(R.styleable.RevealView_colorBar, getResources().getColor(R.color.colorAccent)));
                }
                if(typedArray.hasValue(R.styleable.RevealView_contentBar)){
                    bottomBar.setBackgroundColor(typedArray.getColor(R.styleable.RevealView_contentBar, getResources().getColor(R.color.colorPrimary)));
                }
                if(typedArray.hasValue(R.styleable.RevealView_message)){
                    bottomBarText.setText(typedArray.getString(R.styleable.RevealView_message));
                }
            } finally {
                typedArray.recycle();
            }
        }

    }
}
