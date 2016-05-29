package me.codego.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 流式布局FlowLayout
 * Created by mengxn on 16/5/10.
 */
public class FlowLayout extends FrameLayout {

    private int divideHeight;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = getMeasuredWidth();

        int maxWidth = 0;
        int maxHeight = 0;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            LayoutParams params = (LayoutParams) childView.getLayoutParams();
            if (maxWidth + childWidth > parentWidth) {
                maxHeight += lineHeight + divideHeight;
                params.topMargin = maxHeight;
                params.leftMargin = 0;
                maxWidth = childWidth + divideHeight;
            } else {
                params.leftMargin = maxWidth;
                params.topMargin = maxHeight;
                maxWidth += childWidth + divideHeight;
            }
            lineHeight = Math.max(lineHeight, childHeight);
        }
    }

    public void setDivideHeight(int divideHeight) {
        this.divideHeight = divideHeight;
    }
}
