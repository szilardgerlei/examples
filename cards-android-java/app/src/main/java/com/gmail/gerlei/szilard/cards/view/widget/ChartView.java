package com.gmail.gerlei.szilard.cards.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmail.gerlei.szilard.cards.R;
import com.gmail.gerlei.szilard.cards.utils.DecimalUtil;
import com.gmail.gerlei.szilard.cards.utils.DimensionUtil;

import androidx.core.content.res.ResourcesCompat;

public class ChartView extends RelativeLayout {

    private static final int INITIAL_VALUE = -1;
    private static final int OFFSET_DINAMIC_SUBVIEW = 6;
    private static final int DEFAULT_ANIM_DURATION = 800;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final float ALPHA_INVISIBLE = 0.0f;
    private static final int CHART_DINAMIC_SUBVIEW_WIDTH = 12;
    private int currentValue = INITIAL_VALUE;
    private boolean lastCardAvailableBalanceWasZero = false;

    private Paint paint;
    private Path path;

    private TextView availableBalanceTv;
    private ImageView alertIconIv;

    private SliderMode sliderMode = SliderMode.SHOW;
    private int colorRed;
    private int colorPrimaryDark;

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.layout_chart, this);
        availableBalanceTv = findViewById(R.id.available_balance_tv);
        alertIconIv = findViewById(R.id.alert_icon_iv);
        colorRed = ResourcesCompat.getColor(getResources(), R.color.colorRed, null);
        colorPrimaryDark = ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null);
        setWillNotDraw(false);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(colorPrimaryDark);
        paint.setStrokeWidth(DimensionUtil.dpToPx(getContext(), CHART_DINAMIC_SUBVIEW_WIDTH));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (currentValue != INITIAL_VALUE && sliderMode.equals(SliderMode.SHOW)) {
            path = new Path();
            path.moveTo(getWidthWithOffset(), getHeight() / 2.0f);
            path.lineTo(currentValue, getHeight() / 2.0f);
            canvas.drawPath(path, paint);
        } else if (sliderMode.equals(SliderMode.HIDE) && path != null) {
            path.reset();
            canvas.drawPath(path, paint);
        }
    }

    public void setBalances(int currentBalance, int availableBalance) {
        availableBalanceTv.setText(DecimalUtil.formatByDefaultDecimalFormat(availableBalance));

        float maxValue = currentBalance + availableBalance;
        float proratedValue = (float) availableBalance / maxValue; // Value is always under than 1.

        int realMaxValue = getWidthWithOffset();
        float realProratedValue = realMaxValue * proratedValue;

        float reversedValue = realMaxValue - realProratedValue;
        setChart(reversedValue);
    }

    private void setChart(float newValue) {
        if (currentValue == -1) {
            currentValue = getWidthWithOffset();
        }

        if (newValue != getWidthWithOffset() && lastCardAvailableBalanceWasZero) {
            setVisibleAlertIcon(false);
            availableBalanceTv.setTextColor(colorPrimaryDark);
            lastCardAvailableBalanceWasZero = false;
            currentValue = getWidthWithOffset();
            sliderMode = SliderMode.SHOW;
            startAnim(newValue);
        } else if (newValue == getWidthWithOffset() && !lastCardAvailableBalanceWasZero) {
            lastCardAvailableBalanceWasZero = true;
            availableBalanceTv.setTextColor(colorRed);
            setVisibleAlertIcon(true);
            sliderMode = SliderMode.HIDE;
            requestLayout();
        } else if (newValue != getWidthWithOffset()) {
            startAnim(newValue);
        }
    }

    private void startAnim(float newValue) {
        ChartAnimation anim = new ChartAnimation();
        anim.oldValue = currentValue;
        anim.newValue = newValue;
        startAnimation(anim);
    }

    private void setVisibleAlertIcon(boolean visible) {
        alertIconIv.setAlpha(visible ? ALPHA_VISIBLE : ALPHA_INVISIBLE);
    }

    private int getWidthWithOffset() {
        return (int) (getWidth() - DimensionUtil.dpToPx(getContext(), OFFSET_DINAMIC_SUBVIEW));
    }

    private enum SliderMode {
        HIDE, SHOW
    }

    class ChartAnimation extends Animation {
        float oldValue;
        float newValue;

        ChartAnimation() {
            setDuration(DEFAULT_ANIM_DURATION);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            float calculatedAnimValue = oldValue + ((newValue - oldValue) * interpolatedTime);
            currentValue = (int) calculatedAnimValue;
            requestLayout();
        }
    }

}


