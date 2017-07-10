package com.example.sampaniccia.learningthings.Visual;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sampaniccia.learningthings.R;

/**
 * Divider that separates the pinned "special" announcements from the normal ones
 */

public class AnnouncementSpecialDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public AnnouncementSpecialDivider(Drawable d) {
        mDivider = d;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = mDivider.getIntrinsicHeight();

    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft() + 5;
        int dividerRight = parent.getWidth() - parent.getPaddingRight() - 5;

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            Boolean drawSpecialDivider = (Boolean) child.getTag(R.id.drawSpecialDivider);

            if (drawSpecialDivider) {
                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }


    }
}
