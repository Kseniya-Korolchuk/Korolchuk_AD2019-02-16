package by.korolchuk.dz4;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.RectF;
        import android.support.annotation.Nullable;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;

public class MyView extends View {

    private float padding = getWidth() * 0.03f;
    private float side = (getWidth() - padding * 4) / 3;
    Paint square = new Paint();
    Paint paint = new Paint();

    private float pressedX;
    private float pressedY;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        square.setColor(Color.DKGRAY);
        square.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            pressedX = event.getX();
            pressedY = event.getY();
            invalidate();
        }
        return  true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float radius = Math.min(cx, cy);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

        canvas.drawCircle(cx, cy, radius, paint);

        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setAntiAlias(true);

        int heightRect = Math.max(getWidth(), getHeight()) / 3;
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = cy - heightRect / 2;
        rectF.right = getWidth();;
        rectF.bottom = rectF.top + heightRect;
        canvas.drawRect(rectF, paint2);*/


        RectF sqr = new RectF();

        sqr.left = padding;
        sqr.top = padding;
        sqr.right = sqr.left + side;
        sqr.bottom = sqr.left + side;

        canvas.drawRect(sqr, square);

        sqr.left = sqr.left + side + padding;
        sqr.right = sqr.right + side + padding;

        canvas.drawRect(sqr, square);

        sqr.left = sqr.left + side + padding;
        sqr.right = sqr.right + side + padding;

        canvas.drawRect(sqr, square);

        canvas.drawCircle(pressedX, pressedY, 10, paint);

        Paint arc = new Paint();
        arc.setColor(Color.GRAY);
        arc.setAntiAlias(true);




    }
}
