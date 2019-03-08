package by.korolchuk.dz4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

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

    public void setData(float[] data) {
        this.data = data;
        invalidate();
    }

    private float[] pieSegment() {
        float[] segValues = new float[this.data.length];
        float Total = getTotal();

        for (int i = 0; i < this.data.length; i++) {
            segValues[i] = (this.data[i] / Total) * 360;
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

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (data != null) {
            int left = getWidth() / 4;
            int top = getWidth() / 4;
            int endRight = getWidth() - getWidth() / 4;
            int endBottom = getWidth() - getWidth() / 4;
            RectF rectF = new RectF(left, top, endRight, endBottom);
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
}
