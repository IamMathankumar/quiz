package ppts.com.quiz.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import ppts.com.quiz.utils.ScreenUtils;

/**
 * Created by mathankumar.k on 22-11-2017.
 */

public class RelativeLayoutHeight extends RelativeLayout {
    public RelativeLayoutHeight(Context context) {
        super(context);
    }

    public RelativeLayoutHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredWidth();
        setMeasuredDimension(width, height/3);
    }
*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight= ScreenUtils.getScreenHeight(getContext());
        if(MeasureSpec.getSize(heightMeasureSpec) > maxHeight/3) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight/3, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
