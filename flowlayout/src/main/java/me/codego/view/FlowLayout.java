package me.codego.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (MeasureSpec.EXACTLY == widthMode) {
            final int limitWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
            int marginTop = 0;
            int marginStart = 0;
            int itemHeight = 0;
            for (int i = 0; i < getChildCount(); i++) {
                final View child = getChildAt(i);
                final ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (childWidth == 0 || childHeight == 0) {
                    // child height should not be LayoutParams.WRAP_CONTENT, so we change it
                    if (layoutParams.height == LayoutParams.MATCH_PARENT) {
                        layoutParams.height = LayoutParams.WRAP_CONTENT;
                    }
                    child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), layoutParams.width),
                            getChildMeasureSpec(widthMeasureSpec, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                    childWidth = child.getMeasuredWidth();
                    childHeight = child.getMeasuredHeight();
                }
                itemHeight = Math.max(itemHeight, childHeight);

                if (marginStart + childWidth + horizontalSpacing > limitWidth) {
                    marginTop += (itemHeight + verticalSpacing);
                    marginStart = 0;
                    itemHeight = childHeight;
                }

                layoutParams.leftMargin = marginStart;
                layoutParams.topMargin = marginTop;

                marginStart += (childWidth + horizontalSpacing);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }
}
