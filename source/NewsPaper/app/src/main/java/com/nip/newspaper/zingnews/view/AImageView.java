package com.nip.newspaper.zingnews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by nguyenminhthang on 6/14/15.
 */
public class AImageView extends ImageView {

    private float ration = 2/3;

    public AImageView(Context context) {
        super(context);
    }

    public AImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()*2/3);
    }


    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
//        ration = bm.getHeight()/bm.getWidth();
//        requestLayout();
    }
}
