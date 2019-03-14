package com.mcmo.z.commonlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SpaceDecoration";
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int orientation;
    private int startSpace, endSpace;
    private Drawable mDrawable;
    private Rect mBound = new Rect();
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public SpaceDecoration(Context context, int orientation) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDrawable = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public SpaceDecoration(Drawable drawable, int orientation) {
        this.mDrawable = drawable;
        setOrientation(orientation);
    }
    private void setOrientation(int orientation) {
        if (orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        } else {
            this.orientation = orientation;
        }
    }

    public void setStartSpace(int startSpace) {
        this.startSpace = startSpace;
    }

    public void setEndSpace(int endSpace) {
        this.endSpace = endSpace;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && mDrawable != null && parent.getAdapter() != null) {
            if (orientation == VERTICAL) {
                drawVertical(c, parent, state);
            } else {
                drawHorizontal(c, parent, state);
            }
        }
    }

    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        c.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            c.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        final int viewCount = parent.getChildCount();

        for (int i = 0; i < viewCount; i++) {
            final View child = parent.getChildAt(i);
            final int index = parent.getChildAdapterPosition(child);
            if (index == parent.getAdapter().getItemCount() - 1) {
                continue;
            }
            parent.getDecoratedBoundsWithMargins(child, mBound);
            int bottom = mBound.bottom + Math.round(child.getTranslationY());
            int top = bottom - mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
        c.restore();
    }

    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        c.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            if (parent.getClipToPadding()) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                c.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
            } else {
                top = 0;
                bottom = parent.getHeight();
            }
            final int viewCount = parent.getChildCount();

            for (int i = 0; i < viewCount; i++) {
                final View child = parent.getChildAt(i);
                final int index = parent.getChildAdapterPosition(child);
                if (index == parent.getAdapter().getItemCount() - 1) {
                    continue;
                }
                parent.getDecoratedBoundsWithMargins(child, mBound);
                int right = mBound.right + Math.round(child.getTranslationX());
                int left = right - mDrawable.getIntrinsicWidth();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }
        c.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int left = 0, top = 0, right = 0, bottom = 0;
        if (orientation == VERTICAL) {
            left = right = 0;
            bottom = mDrawable == null ? 0 : mDrawable.getIntrinsicHeight();
            if (position == 0) {
                top = Math.max(0, startSpace);
            } else if (position == parent.getAdapter().getItemCount() - 1) {
                bottom = Math.max(0, endSpace);
            }
        } else {
            top = bottom = 0;
            right = mDrawable == null ? 0 : mDrawable.getIntrinsicWidth();
            if (position == 0) {
                left = Math.max(0, startSpace);
            } else if (position == parent.getAdapter().getItemCount() - 1) {
                right = Math.max(0, endSpace);
            }
        }
        outRect.set(left, top, right, bottom);
    }
}
