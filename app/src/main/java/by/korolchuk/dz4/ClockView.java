package by.korolchuk.dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClockView extends View {



    private int width = 0;
    private int height = 0;
    private int fontSize = 0;
    private int padding = 0;
    private int hand = 0;
    private int hourHand = 0;
    private int radius = 0;
    private Paint paint;
    private boolean isInit;
    private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();



    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    private void initClock() {
        width = getWidth();
        height = getHeight();
        int numeralSpacing = 0;
        padding = numeralSpacing + 50;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min/2 - padding;
        hand = min/20;
        hourHand = min/7;
        paint = new Paint();
        isInit = true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        drawCenter(canvas);
        drawHands(canvas);
        drawNumeral(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawNumeral(Canvas canvas) {
        //canvas.drawLine(width/2, padding, width/2 - 3, 0, paint);
        for (int i = 0; i < 13; i++)
         {
            canvas.drawLine(width/2, padding, width/2 - 3, 0, paint);
            canvas.rotate(30, width/2, height/2);
         }
       /* paint.setTextSize(fontSize);

        for (int number : numbers) {
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = Math.PI / 6 * (number -3);
            int x = (int) (width/2 + Math.cos(angle) * radius - rect.width()/2);
            int y = (int) (height/2 + Math.sin(angle) * radius - rect.height()/2);
            canvas.drawText(tmp, x, y, paint);
        }*/

    }

    private  void drawHand(Canvas canvas, double loc, boolean isHour){
        double angle = Math.PI * loc/30 - Math.PI/2;
        int handRadius = isHour ? radius  - hand - hourHand : radius - hand;
        canvas.drawLine((float) width/2, (float) height/2,
                (float) (width/2 + Math.cos(angle) * handRadius),
                (float) (height/2 + Math.sin(angle) * handRadius),
                paint);
    }

    private void drawHands(Canvas canvas) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas,(hour + (float)calendar.get(Calendar.MINUTE) / 60) * 5f,true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas, calendar.get(Calendar.SECOND), false);
    }

    private void drawCenter(Canvas canvas) {
    paint.setStyle(Paint.Style.FILL);
    canvas.drawCircle((float) width/2, (float) height/2, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
    paint.reset();
    paint.setColor(getResources().getColor(android.R.color.white));
    paint.setStrokeWidth(5);
    paint.setStyle(Paint.Style.STROKE);
    paint.setAntiAlias(true);
    canvas.drawCircle((float) width/2, (float) height/2, radius + padding -10, paint);
    }
}
