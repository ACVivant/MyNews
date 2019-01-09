package com.vivant.annecharlotte.mynews.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Anne-Charlotte Vivant on 09/01/2019.
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * Attributes of separator  android.R.attr.listDivider
     */
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    // static VERTICAL_LIST
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public MyDividerItemDecoration(Context context) {
        // get the attributes style
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        // get drawable in style
        mDivider = a.getDrawable(0);
        a.recycle();
        // set orientation
        mOrientation = VERTICAL_LIST;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        // get padding of parents (Left and right
        final int left = parent.getPaddingLeft()+225;
        final int right = parent.getWidth() - parent.getPaddingRight();

        // get the count of child
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            // create layoutParams
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            // calculate new padding child
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            // set bounds
            mDivider.setBounds(left, top, right, bottom);
            // draw in canvas
            mDivider.draw(c);
        }
    }

    /**
     * Return the dimension outRect for itemPosition and parent (Offset)
     * @param outRect : the new Rect
     * @param itemPosition: the position of item
     * @param parent : the view parent
     *
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        // depends orientation
            // return rect with height of divider
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
