package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.truba.touchgallery.TouchView.UrlTouchImageView;

/**
 * Created by fabio on 28/05/14.
 */
public class InfinityUrlAdapter extends BasePagerAdapter {

    private int TOTAL_PAGES = -1;
    private int MIN_LOOPS = 1000;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to test your "infinite" ViewPager :D
    public int FIRST_PAGE = 1;
    private ImageView.ScaleType mScaleType = null;
    private OnItemClickListener mListener = null;

    public InfinityUrlAdapter(Context context, List<String> resources, OnItemClickListener listener) {
        super(context, resources);
        TOTAL_PAGES = resources.size();
        FIRST_PAGE = TOTAL_PAGES * MIN_LOOPS / 2;
        mListener = listener;
    }

    public InfinityUrlAdapter(Context context, List<String> resources) {
        super(context, resources);
        TOTAL_PAGES = resources.size();
        FIRST_PAGE = TOTAL_PAGES * MIN_LOOPS / 2;
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, FIRST_PAGE/*position*/, object);
        ((GalleryViewPager)container).mCurrentView = ((UrlTouchImageView)object).getImageView();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        position = position % TOTAL_PAGES;
        final int _positionForOnClick = position;

        final UrlTouchImageView iv = new UrlTouchImageView(mContext);
        if(mListener != null) {
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemChange(view, _positionForOnClick);
                }
            });
        }
        iv.setUrl(mResources.get(position));
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if(mScaleType != null)
            iv.setScaleType(mScaleType);
        collection.addView(iv, 0);
    return iv;
    }

    /**
     * Set Scaletype for ImageView
     * @param scaletype
     */
    public void setScaleTypeForImageView(ImageView.ScaleType scaletype) {
        mScaleType = scaletype;
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES * MIN_LOOPS;
    }
};