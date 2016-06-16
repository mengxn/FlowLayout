package me.codego.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 流式布局FlowLayout
 * Created by mengxn on 16/5/10.
 */
public class FlowLayout extends FrameLayout {

    private int horizontalSpacing;
    private int verticalSpacing;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        horizontalSpacing = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_horizontalSpacing, 0);
        verticalSpacing = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_verticalSpacing, 0);
        typedArray.recycle();
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
                maxHeight += lineHeight + verticalSpacing;
                params.topMargin = maxHeight;
                params.leftMargin = 0;
                maxWidth = childWidth + horizontalSpacing;
            } else {
                params.leftMargin = maxWidth;
                params.topMargin = maxHeight;
                maxWidth += childWidth + horizontalSpacing;
            }
            lineHeight = Math.max(lineHeight, childHeight);
        }
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }
}
