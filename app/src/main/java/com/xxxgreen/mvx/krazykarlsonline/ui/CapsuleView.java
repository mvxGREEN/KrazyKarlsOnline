package com.xxxgreen.mvx.krazykarlsonline.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.xxxgreen.mvx.krazykarlsonline.R;


public class CapsuleView extends AppCompatTextView {
    public CapsuleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CapsuleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        @SuppressLint("DrawAllocation")
        Paint p = new Paint();

        @SuppressLint("DrawAllocation")
        Rect rect = new Rect(
                3,40,3,40);
        canvas.drawColor(getResources().getColor(R.color.colorPrimaryLight));
        @SuppressLint("DrawAllocation")
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect( rectF, 3,3, p);
    }
}
