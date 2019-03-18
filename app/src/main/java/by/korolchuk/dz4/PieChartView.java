package by.korolchuk.dz4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import by.korolchuk.R;

public class PieChartView extends View {

    private Paint piePaint;
    private float[] data;


    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.FILL);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        drawPieChart(canvas);
        drawLines(canvas);
    }

    public void setData(float[] data) {
        this.data = data;
        invalidate();
    }

    private float[] pieSegment() {
        float[] segValues = new float[this.data.length];
        float total = getTotal();

        for (int i = 0; i < this.data.length; i++) {
            segValues[i] = (this.data[i] / total) * 360;
        }
        return segValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.data) {
            total += val;
        }
        return total;
    }

    private void drawPieChart(Canvas canvas) {
        if (data != null) {
            int left = getWidth() / 4;
            int top = getWidth() / 4;
            int right = getWidth() - getWidth() / 4;
            int bottom = getWidth() - getWidth() / 4;
            RectF rectF = new RectF(left, top, right, bottom);
            float[] segment = pieSegment();
            float segStartPoint = 0;
            for (float aSegment : segment) {
                Random rnd = new Random();
                int color = Color.argb(255, (int) aSegment, rnd.nextInt(256), rnd.nextInt(256));
                piePaint.setColor(color);
                canvas.drawArc(rectF, segStartPoint, aSegment, true, piePaint);

                segStartPoint += aSegment;
            }
        }
    }

    private void drawLines(Canvas canvas) {
        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        float[] degrees = pieSegment();

        canvas.rotate(degrees[0] / 2, getWidth() / 2f, getWidth() / 2f);

        for (int i = 0; i < degrees.length - 1; i++) {
            String tmp = String.valueOf(data[i]);
            drawLine(canvas);
            canvas.drawText(
                    tmp,
                    (getWidth() - getWidth() / 4f) + 60,
                    getWidth() / 2f,
                    textPaint);
            canvas.rotate(degrees[i] / 2 + degrees[i + 1] / 2, getWidth() / 2f, getWidth() / 2f);
        }
        drawLine(canvas);
        canvas.drawText(
                String.valueOf(data[data.length - 1]),
                (getWidth() - getWidth() / 4f) + 60,
                getWidth() / 2f,
                textPaint);
    }

    private void drawLine(Canvas canvas) {
        float xStart = getWidth() - getWidth() / 4f;
        float yStart = getWidth() / 2f;
        float xEnd = (getWidth() - getWidth() / 4f) + 40;
        float yEnd = getWidth() / 2f;
        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(getResources().getColor(R.color.gray));

        canvas.drawLine(xStart, yStart, xEnd, yEnd, linePaint);
        canvas.drawCircle(xEnd, yEnd, 10, linePaint);
    }
}
