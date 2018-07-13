package net.kehui.www.cursor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by IF on 2018/5/30.
 */

public class Cursor extends View {
    private float progress =10;
    private Paint drawWave;

    public Cursor(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Paint paint;
        canvas.drawLine(progress, 0, progress,300,drawWave);
    }
}
