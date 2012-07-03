package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import ru.truba.touchgallery.TouchView.TouchImageView;

/**
 This class implements method to help <b>TouchImageView</b> fling, draggin and scaling.
 */
public class GalleryViewPager extends ViewPager {
    PointF last;
    public TouchImageView mCurrentView;
    public GalleryViewPager(Context context) {
        super(context);
    }
    private float[] handleMotionEvent(MotionEvent event)
    {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                last = new PointF(event.getX(0), event.getY(0));
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                PointF curr = new PointF(event.getX(0), event.getY(0));
                return new float[]{curr.x - last.x, curr.y - last.y};

        }
        return null;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
        {
            super.onTouchEvent(event);
        }

        float [] difference = handleMotionEvent(event);

        if (mCurrentView.pagerCanScroll()) {
            return super.onTouchEvent(event);
        }
        else {
            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
            {
                return super.onTouchEvent(event);
            }
            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
            {
                return super.onTouchEvent(event);
            }
            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
            {
                return super.onTouchEvent(event);
            }
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
        {
            super.onInterceptTouchEvent(event);
        }

        float [] difference = handleMotionEvent(event);

        if (mCurrentView.pagerCanScroll()) {
            return super.onInterceptTouchEvent(event);
        }
        else {
            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
            {
                return super.onInterceptTouchEvent(event);
            }
            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
            {
                return super.onInterceptTouchEvent(event);
            }
            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
            {
                return super.onInterceptTouchEvent(event);
            }
        }
        return false;
    }
}
