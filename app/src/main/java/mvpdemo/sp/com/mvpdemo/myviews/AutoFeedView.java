package mvpdemo.sp.com.mvpdemo.myviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * addView添加有点击回调的TextView，并自动换行
 * Created by songyuan on 2017/6/19.
 */

public class AutoFeedView extends ViewGroup {
    private static final int CHILD_TOP_BOTTOM_PADDING = 5;//子组件内部上下的padding
    private static final int CHILD_LEFT_RIGHT_PADDING = 10;//子组件内部左右的padding
    private static final int CHILD_LINE_ROW_MARGIN = 10;//子组件之间的margin

    public AutoFeedView(Context context) {
        super(context);
    }

    public AutoFeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取根据xml配置的layout_width模式得到的实际宽度
        int realWidth = MeasureSpec.getSize(widthMeasureSpec) - CHILD_LEFT_RIGHT_PADDING * 2;
        int addViewsWidth = 0;//子组件累加后的宽度
        int addViewsHeight = 0;//子组件一行或多行累加后的高度
        int rowCount = 1;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setPadding(CHILD_LEFT_RIGHT_PADDING, CHILD_TOP_BOTTOM_PADDING, CHILD_LEFT_RIGHT_PADDING, CHILD_TOP_BOTTOM_PADDING);
            childView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            addViewsWidth = addViewsWidth + childWidth + CHILD_LEFT_RIGHT_PADDING;
            //当addViewWidth长度大于父组件实际宽度，换行
            if (addViewsWidth > realWidth) {
                rowCount++;
                addViewsWidth = childWidth + CHILD_LEFT_RIGHT_PADDING;
            }
            addViewsHeight = rowCount * (childHeight + CHILD_LINE_ROW_MARGIN);
        }
        //设置计算结果，重写onMeasure必须
        setMeasuredDimension(realWidth, addViewsHeight);
    }
}
