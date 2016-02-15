package com.hrules.darealmvp.sample.presentation.adapters.commons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * https://gist.github.com/hrules6872/40bea787e739fc7c3ab0
 **/

public class ColorDividerItemDecoration extends RecyclerView.ItemDecoration {
  private static final int DEFAULT_DIVIDER_COLOR = Color.GRAY;
  private static final float DEFAULT_DIVIDER_SIZE = 1f;
  private static final boolean DEFAULT_DIVIDER_SHOW_FIRST = false;
  private static final boolean DEFAULT_DIVIDER_SHOW_LAST = false;

  private final boolean showFirstDivider;
  private final boolean showLastDivider;
  private final int size;
  private final Paint paint;

  public ColorDividerItemDecoration() {
    this(DEFAULT_DIVIDER_COLOR, DEFAULT_DIVIDER_SIZE, DEFAULT_DIVIDER_SHOW_FIRST,
        DEFAULT_DIVIDER_SHOW_LAST);
  }

  public ColorDividerItemDecoration(@ColorInt int color) {
    this(color, DEFAULT_DIVIDER_SIZE, DEFAULT_DIVIDER_SHOW_FIRST, DEFAULT_DIVIDER_SHOW_LAST);
  }

  public ColorDividerItemDecoration(@ColorInt int color, float size) {
    this(color, size, DEFAULT_DIVIDER_SHOW_FIRST, DEFAULT_DIVIDER_SHOW_LAST);
  }

  public ColorDividerItemDecoration(@ColorInt int color, float size, boolean showFirstDivider,
      boolean showLastDivider) {
    paint = new Paint();
    paint.setColor(color);
    paint.setStrokeWidth(size);
    this.size = (int) size;

    this.showFirstDivider = showFirstDivider;
    this.showLastDivider = showLastDivider;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (size <= 0) {
      return;
    }

    if (showFirstDivider && parent.getChildLayoutPosition(view) < 1
        || parent.getChildLayoutPosition(view) >= 1) {
      if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
        outRect.top = size;
      } else {
        outRect.left = size;
      }
    }

    if (showLastDivider && parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
      if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
        outRect.bottom = size;
      } else {
        outRect.right = size;
      }
    }
  }

  private int getTotalItemCount(RecyclerView parent) {
    return parent.getAdapter().getItemCount();
  }

  @Override public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();
    int top = parent.getPaddingTop();
    int bottom = parent.getHeight() - parent.getPaddingBottom();

    int orientation = getOrientation(parent);
    int childCount = parent.getChildCount();

    for (int i = showFirstDivider ? 0 : 1; i < childCount; i++) {
      View child = parent.getChildAt(i);
      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      if (orientation == LinearLayoutManager.VERTICAL) {
        top = child.getTop() - params.topMargin;
        bottom = top - (size / 2);

        canvas.drawLine(left, bottom, right, bottom, paint);
      } else {
        left = child.getLeft() - params.leftMargin;
        right = left - (size / 2);

        canvas.drawLine(right, top, right, bottom, paint);
      }
    }

    if (showLastDivider && childCount > 0) {
      View child = parent.getChildAt(childCount - 1);
      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      if (orientation == LinearLayoutManager.VERTICAL) {
        top = child.getBottom() + params.bottomMargin;
        bottom = top + size;

        canvas.drawLine(left, bottom, right, bottom, paint);
      } else {
        left = child.getRight() + params.rightMargin;
        right = left + (size / 2);

        canvas.drawLine(right, top, right, bottom, paint);
      }
    }
  }

  private int getOrientation(RecyclerView parent) {
    if (parent.getLayoutManager() instanceof LinearLayoutManager) {
      LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
      return layoutManager.getOrientation();
    } else {
      throw new IllegalStateException(
          "ColorDividerItemDecoration can only be used with a LinearLayoutManager.");
    }
  }
}