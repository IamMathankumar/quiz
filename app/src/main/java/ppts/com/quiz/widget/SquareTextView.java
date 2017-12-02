package ppts.com.quiz.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mathankumar.k on 22-11-2017.
 */

public class SquareTextView extends AppCompatTextView {
    public SquareTextView(Context context) {
        super(context);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredWidth();
        setMeasuredDimension(width, height);
    }


}
