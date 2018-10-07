package as.bwei.com.water;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HP on 2018/10/7.
 */

public class WaterView extends View{

    private int color_02;
    private int color_01;
    private Paint mPaintTop,mPaintBottom;
    private Path mPathTop,mPathBottom;

    private float φ;

    public WaterView(Context context) {
        super(context);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ty = context.obtainStyledAttributes(attrs,R.styleable.WaterView);
        color_01 = ty.getColor(R.styleable.WaterView_color_01,0);
        color_02 = ty.getColor(R.styleable.WaterView_color_02,0);
        ty.recycle();

        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaintTop = new Paint();
        mPaintTop.setColor(color_01);
        mPaintTop.setAntiAlias(true);

        mPaintBottom = new Paint();
        mPaintBottom.setColor(color_02);
        mPaintBottom.setAntiAlias(true);
        mPaintBottom.setAlpha(50);

        mPathTop=new Path();
        mPathBottom=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathTop.reset();
        mPathBottom.reset();

        //路径起始位置
        mPathTop.moveTo(getLeft(),getBottom());
        mPathBottom.moveTo(getLeft(),getBottom());

        //获取每个宽度值所占的度数
        double mY=Math.PI*2/getWidth();

        φ+=0.1f;

        //路径移动的坐标
        for (float x=0;x<=getWidth();x+=20){
                    float y=(float) (10*Math.cos(mY*x+φ)+10);
                    mPathTop.lineTo(x, y);
                    mPathBottom.lineTo(x, (float) (10*Math.sin(mY*x+φ)));
                    listener.animation(y);
                }
        //路径终止的位置
        mPathTop.lineTo(getRight(),getBottom());
        mPathBottom.lineTo(getRight(),getBottom());

        canvas.drawPath(mPathTop,mPaintTop);
        canvas.drawPath(mPathBottom,mPaintBottom);

        postInvalidateDelayed(20);//刷新
    }
    /**
     * 为什么要创建接口
     *
     * 就是通过接口把当前浮动的一个Y值，回调给调用的页面，
     * */
    private AnimationListener listener;

    //传递接口
    public void animation(AnimationListener listener){
        this.listener=listener;
    }

    public interface AnimationListener{
        void animation(float y);
    }
}
