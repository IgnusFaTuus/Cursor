package net.kehui.www.cursor;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.support.annotation.Nullable;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;

        import java.math.BigDecimal;

/**
 * Created by IF on 2018/5/30.
 */

public class Cursor extends View {

    private Paint mTextPaint;
    private Paint mCursorPaint;
    private float progress = 10;
    private int viewHeight;
    private int viewWidth;
    private boolean isCanMove;

    public Cursor(Context context) {
        super(context);
        init();
    }

    public Cursor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Cursor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.CYAN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setTextSize(48);
        //
        mCursorPaint = new Paint();
        mCursorPaint.setAntiAlias(true);
        mCursorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCursorPaint.setColor(Color.RED);
        mCursorPaint.setStrokeWidth(4);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.restore();
        canvas.drawLine(progress, 0, progress, viewHeight, mCursorPaint);
        canvas.drawCircle(progress, viewHeight, 10, mCursorPaint);
        BigDecimal bd = new BigDecimal((progress - 18) / 180);
        bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        mTextPaint.setTextSize(48);
        canvas.drawText(bd.floatValue() + "m", viewWidth -100, 100, mTextPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isCanMove = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isCanMove) {
                    return false;
                }
                float x = event.getX() - 10;
                progress = x;
                invalidate();
                break;
        }
        return true;
    }
}