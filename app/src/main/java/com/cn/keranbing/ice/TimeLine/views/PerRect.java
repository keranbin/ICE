package com.cn.keranbing.ice.TimeLine.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by keranbin on 2017/3/29.
 */

public class PerRect extends View{
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Path path;


    public PerRect(Context context) {
        super(context);
        initPaint();
    }

    public PerRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PerRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int height=getHeight();
//        int radius=Math.min(width,height)/2;
//        canvas.drawCircle(width/2,height/2,radius,mPaint);
        path=new Path();
        path.moveTo(0,0);
        path.lineTo(0,height-400);
        path.lineTo(width,height);
        path.lineTo(width,0);


        path.close();
        canvas.drawPath(path,mPaint);
    }
}