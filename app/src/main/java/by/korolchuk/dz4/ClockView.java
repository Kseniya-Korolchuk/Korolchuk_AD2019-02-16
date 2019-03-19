package by.korolchuk.dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import java.util.Calendar;

public class ClockView extends View {


    private float width = 0;
    private float height = 0;
    private int fontSize = 25;
    private int padding = 0;
    private float hand = 0;
    private float hourHand = 0;
    private float radius = 0;
    private Paint paint;
    private boolean isInit;


    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initClock() {
        width = getWidth();
        height = getHeight();
        int numeralSpacing = 0;
        padding = numeralSpacing + 50;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                getResources().getDisplayMetrics());
        float min = Math.min(height, width);
        radius = min / 2 - padding;
        hand = min / 20;
        hourHand = min / 7;
        paint = new Paint();
        isInit = true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        canvas.drawColor(Color.DKGRAY);
        drawCircle(canvas);
        drawCenter(canvas);
        drawHands(canvas);
        drawNumbers(canvas);
        drawLines(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawNumbers(Canvas canvas) {
        paint.setTextSize(fontSize);
        canvas.drawText("12", width / 2 - width * 0.02f, padding + 30, paint);
        canvas.drawText("3", width - (padding + 25), height / 2 + height * 0.015f, paint);
        canvas.drawText("6", width / 2 - width * 0.01f, height - (padding + 10), paint);
        canvas.drawText("9", padding + 10, height / 2 + height * 0.015f, paint);
    }

    private void drawLines(Canvas canvas) {

        for (int i = 0; i < 13; i++) {
            canvas.drawLine(width / 2, padding, width / 2, 5, paint);
            canvas.rotate(30, width / 2, height / 2);
        }
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        float handRadius = isHour ? radius - hand - hourHand : radius - hand;
        canvas.drawLine(width / 2, height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),
                paint);
    }

    private void drawHands(Canvas canvas) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + (float) calendar.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas, calendar.get(Calendar.SECOND), false);
    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle( width / 2, height / 2, radius + padding - 10, paint);
    }
}
