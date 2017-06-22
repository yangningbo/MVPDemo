package sp.com.mvpdemo.my_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * addView添加有点击回调的TextView，并自动换行
 * Created by songyuan on 2017/6/19.
 */

public class AutoFeedView extends ViewGroup {
    private int CHILD_TOP_BOTTOM_PADDING = 0;//子组件内部上下的padding
    private int CHILD_LEFT_RIGHT_PADDING = 0;//子组件内部左右的padding
    private int CHILD_LINE_ROW_MARGIN = 0;//子组件之间的margin
    private OnItemClickListener mOnItemClickListener;

    public AutoFeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPaddingMargin(context);
    }

    public AutoFeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPaddingMargin(context);
    }

    public interface OnItemClickListener {
        void onItemClick(ViewGroup parent, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        child.setClickable(true);
        child.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(AutoFeedView.this, v);
                }
            }
        });

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int xStart = 0;//子view左上角x坐标
        int yStart = 0;//子view左上角y坐标
        int xEnd = CHILD_LINE_ROW_MARGIN;//子view右下角x坐标(包含右间距,最终需去除间距)
        int yEnd = 0;//子view右下角y坐标(包含下间距,最终需去除间距)
        int rowCount = 1;
        int realWidth = r - l;//此处r是onMeasure已经计算过的宽度的right坐标
        int childChildCount = getChildCount();
        for (int i = 0; i < childChildCount; i++) {
            View childView = getChildAt(i);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            xEnd += childWidth + CHILD_LINE_ROW_MARGIN;
            if (xEnd - CHILD_LINE_ROW_MARGIN > realWidth) {
                rowCount++;
                xEnd = CHILD_LINE_ROW_MARGIN + childWidth + CHILD_LINE_ROW_MARGIN;
            }
            yEnd = CHILD_LINE_ROW_MARGIN + rowCount * (childHeight + CHILD_LINE_ROW_MARGIN);
            xStart = xEnd - (childWidth + CHILD_LINE_ROW_MARGIN);
            yStart = yEnd - (childHeight + CHILD_LINE_ROW_MARGIN);
            Logger.d("onLayout ===> i= "+i+";childChildCount="+childChildCount+";xStart=" + xStart + ";xEnd=" + xEnd + ";childWidth=" + childWidth + ";yStart=" + yStart + ";childHeight=" + childHeight + ";yEnd=" + yEnd+ ";rowCount=" + rowCount+ ";realWidth=" + realWidth);
            childView.layout(xStart, yStart, xEnd - CHILD_LINE_ROW_MARGIN, yEnd - CHILD_LINE_ROW_MARGIN);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取根据xml配置的layout_width模式得到一整行的实际宽度
        int realWidth = MeasureSpec.getSize(widthMeasureSpec) - CHILD_LINE_ROW_MARGIN * 2;
        int addViewsWidth = 0;//子组件累加后的宽度（某一行内）
        int addViewsHeight = 0;//子组件一行或多行累加后的高度
        int rowCount = 1;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setPadding(CHILD_LEFT_RIGHT_PADDING, CHILD_TOP_BOTTOM_PADDING, CHILD_LEFT_RIGHT_PADDING, CHILD_TOP_BOTTOM_PADDING);
            childView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            addViewsWidth += childWidth + CHILD_LINE_ROW_MARGIN;
            //当addViewWidth长度大于父组件一整行的实际宽度，换行
            if (addViewsWidth - CHILD_LINE_ROW_MARGIN > realWidth) {
                rowCount++;
                addViewsWidth = childWidth + CHILD_LINE_ROW_MARGIN;
            }
            if (i == childCount - 1) {
                addViewsHeight = CHILD_LINE_ROW_MARGIN + rowCount * (childHeight + CHILD_LINE_ROW_MARGIN);
            }

        }
        Logger.d("onMeasure ===> realWidth=" + realWidth + ";addViewsHeight=" + addViewsHeight+ ";rowCount=" + rowCount);
        //设置计算结果，重写onMeasure必须的
        setMeasuredDimension(realWidth, addViewsHeight);
    }


    private void setPaddingMargin(Context context) {
        CHILD_TOP_BOTTOM_PADDING = dip2px(context, 5);
        CHILD_LEFT_RIGHT_PADDING = dip2px(context, 10);
        CHILD_LINE_ROW_MARGIN = dip2px(context, 10);

    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
