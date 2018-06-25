package com.aj.user06.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Anjan Debnath on 6/22/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class EmotionalFaceView extends View {

    private static int DEFAULT_FACE_COLOR = Color.YELLOW;
    private static int DEFAULT_EYES_COLOR = Color.BLACK;
    private static int DEFAULT_MOUTH_COLOR = Color.BLACK;
    private static int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static float DEFAULT_BORDER_WIDTH = 4.0f;

    // Paint object for coloring and styling
    private Paint paint = new Paint();
    // Some colors for the face background, eyes and mouth.
    private int faceColor = DEFAULT_FACE_COLOR;
    private int eyesColor = DEFAULT_EYES_COLOR;
    private int mouthColor = DEFAULT_MOUTH_COLOR;
    private int borderColor = DEFAULT_BORDER_COLOR;




    // Face border width in pixels
    private float borderWidth = DEFAULT_BORDER_WIDTH;
    // View size in pixels
    private int size = 320;

    private Path mouthPath = new Path();

    public EmotionalFaceView(Context context) {
        super(context);
        init(null, 0);
    }

    //To create a new View instance from XML.
    public EmotionalFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0); // this will take xml configuration
    }

    //To create a new view instance from XML with a style from theme attribute.
    public EmotionalFaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Calculate the smaller dimension of your view
        size = Math.min(getMeasuredWidth(), getMeasuredHeight());

        //Use setMeasuredDimension(int, int) to store the measured width and measured height of the view, in this case making your view width and height equivalent.
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawFaceBackground(canvas);
        drawEyes(canvas);
        drawMouth(canvas);

    }

    private void init(AttributeSet attrs, int defStyle) {


        paint.setAntiAlias(true);
        setupAttributes(attrs, defStyle);


    }

    private void setupAttributes(AttributeSet attrs, int defStyle) {
        // Obtain a typed array of attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EmotionalFaceView, defStyle, 0);


        //ta.getColor(com.majorarif.customviews.R.styleable.ValueBar_baseColor, Color.BLACK);

        // Extract custom attributes into member variables

        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR);
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR);
        mouthColor = typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR);
        borderColor = typedArray.getColor(R.styleable.EmotionalFaceView_borderColor,
                DEFAULT_BORDER_COLOR);
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth,
                DEFAULT_BORDER_WIDTH);

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle();
    }

    private void drawFaceBackground(Canvas canvas){

        //Set the paint color to the faceColor and make it fill the drawing area.
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);

        //Calculate a radius for a circle which you want to draw as the face background.
        float radius = size / 2f;


        //Draw the background circle with a center of (x,y), where x and y are equal to the half of size, and with the calculated radius.
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);


        //Change the paint color to the borderColor and make it just draw a border around the drawing area by setting the style to STROKE
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        //Draw a border with the same center but with a radius shorter than the previous radius by the borderWidth.
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);

    }


    private void drawEyes(Canvas canvas) {

        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        RectF leftEyeRect = new RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f);
        canvas.drawOval(leftEyeRect, paint);

        RectF rightEyeRect = new RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f);

        canvas.drawOval(rightEyeRect, paint);
    }



    private void drawMouth(Canvas canvas) {
        // Clear
        //mouthPath.reset();

        mouthPath.moveTo(size * 0.22f, size * 0.7f);

        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f);
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f);


        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);

        // Draw mouth path
        canvas.drawPath(mouthPath, paint);
    }
}
