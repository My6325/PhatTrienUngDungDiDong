package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.jspecify.annotations.NonNull;

public class MyCanVas extends View {
    public static int color= Color.YELLOW;

    public MyCanVas(Context context){
        super(context,null);
    }

    public MyCanVas(Context context, AttributeSet attributeSet){
        super(context, attributeSet,0);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(color);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas){
        int pos=10;
        int viewWidth=getWidth();
        int viewHeight=getHeight();
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(pos,viewHeight/2,pos+viewWidth/5,viewHeight,paint);
        paint.setColor(Color.RED);
        canvas.drawRect(pos*2+viewWidth/5,viewHeight/2-100,(pos+viewWidth/5)*2,viewHeight,paint);
    }
}
